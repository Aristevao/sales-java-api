package sales.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import sales.VendasApplication;
import sales.domain.entity.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    private static final long JWT_TOKEN_VALIDITY = 30L;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        LocalDateTime expirationDateTime = LocalDateTime.now().plusMinutes(JWT_TOKEN_VALIDITY);
        Date date = Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts
                .builder()
                .setSubject(user.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValidToken(String token) {
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime date = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(date);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserLogin(String token) throws ExpiredJwtException {
        return getClaims(token).getSubject();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(VendasApplication.class);
        JwtService service = context.getBean(JwtService.class);
        User user = User.builder().login("admin").build();
        String token = service.generateToken(user);
        System.out.println();
        System.out.println();
        System.out.println("TOKEN: " + token);
        System.out.println("IS_VALID_TOKEN: " + service.isValidToken(token));
        System.out.println("UserLogin: " + service.getUserLogin(token));
    }
}
