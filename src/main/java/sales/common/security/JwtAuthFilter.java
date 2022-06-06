package sales.common.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import sales.domain.service.implementation.UserServiceImplementation;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserServiceImplementation userService;

    public JwtAuthFilter(JwtService jwtService, UserServiceImplementation userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        if( authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];
            boolean isValid = jwtService.isValidToken(token);

            if(isValid){
                String userLogin = jwtService.getUserLogin(token);
                UserDetails user = userService.loadUserByUsername(userLogin);
                UsernamePasswordAuthenticationToken userAuthenticationToken = new
                        UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                userAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
