package sales.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sales.domain.entity.User;
import sales.domain.service.implementation.UserServiceImpl;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(CREATED)
    public User saveUser(@RequestBody @Valid User user) {
        String encryptedPassword = passwordEncoder.encode((user.getPassword()));
        user.setPassword(encryptedPassword);
        return userService.saveUser(user);
    }

}
