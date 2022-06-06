package sales.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sales.common.exception.InvalidCredentialsException;
import sales.domain.entity.User;
import sales.domain.repository.UserRepository;
import sales.domain.service.implementation.UserServiceImpl;

@Service
public class AuthenticationService {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    public void authenticateUser(User user) {
        UserDetails userDetails = userService.loadUserByUsername(user.getLogin());
        boolean passwordMatches = passwordEncoder.matches(user.getPassword(), userDetails.getPassword());
        if (passwordMatches) {
            return;
        }
        throw new InvalidCredentialsException("Invalid login or password");
    }
}
