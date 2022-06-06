package sales.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import sales.common.exception.InvalidCredentialsException;
import sales.common.security.JwtService;
import sales.domain.dto.request.JwtRequest;
import sales.domain.dto.response.JwtResponse;
import sales.domain.entity.User;
import sales.domain.service.AuthenticationService;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final JwtService jwtService;

    @PostMapping()
    public JwtResponse authenticate(@RequestBody JwtRequest credentials) {
        try {
            User user = User.builder()
                    .login(credentials.getLogin())
                    .password(credentials.getPassword())
                    .build();
            authService.authenticateUser(user);
            String token = jwtService.generateToken(user);
            return new JwtResponse(user.getLogin(), token);
        } catch (UsernameNotFoundException | InvalidCredentialsException e) {
            throw new ResponseStatusException(UNAUTHORIZED, e.getMessage());
        }
    }
}
