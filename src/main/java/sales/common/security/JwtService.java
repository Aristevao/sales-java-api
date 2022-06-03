package sales.common.security;

import io.jsonwebtoken.JwtBuilder;
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
import java.util.Map;

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

//    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(VendasApplication.class);
//        JwtService service = context.getBean(JwtService.class);
//        User user = User.builder().login("admin").build();
//        String token = service.generateToken(user);
//        System.out.println("TOKEN: " + token);
//    }

//    private String doGenerateToken(Map<String, Object> claims, String subject) {
//        JwtBuilder builder = Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()));
//        if (claims.get("role") != "API") {
//            builder.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY));
//        }
//        return builder.signWith(getSigningKey()).compact();
//    }

}
