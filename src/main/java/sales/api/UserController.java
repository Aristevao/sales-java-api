package sales.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sales.common.exception.InvalidCredentialsException;
import sales.common.security.JwtService;
import sales.domain.dto.request.JwtRequest;
import sales.domain.dto.response.JwtResponse;
import sales.domain.entity.User;
import sales.domain.service.implementation.UserServiceImpl;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(CREATED)
    public User saveUser(@RequestBody @Valid User user) {
        String encryptedPassword = passwordEncoder.encode((user.getPassword()));
        user.setPassword(encryptedPassword);
        return userService.saveUser(user);
    }

    @PostMapping("/auth")
    public JwtResponse authenticate(@RequestBody JwtRequest credentials){
        try{
            User user = User.builder()
                    .login(credentials.getLogin())
                    .password(credentials.getPassword())
                    .build();
            UserDetails userAutenticado = userService.authenticateUser(user);
            String token = jwtService.generateToken(user);
            return new JwtResponse(user.getLogin(), token);
        } catch (UsernameNotFoundException | InvalidCredentialsException e) {
            throw new ResponseStatusException(UNAUTHORIZED, e.getMessage());
        }
    }

}
