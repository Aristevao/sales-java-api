package sales.domain.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sales.common.exception.InvalidCredentialsException;
import sales.domain.entity.User;
import sales.domain.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    public UserDetails authenticateUser(User user) {
        UserDetails userDetails = loadUserByUsername(user.getLogin());
        boolean passwordMatches = encoder.matches(user.getPassword(), userDetails.getPassword());

        if (passwordMatches) {
            return userDetails;
        }

        throw new InvalidCredentialsException("Invalid login or password");
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found"));

        String[] roles = user.isAdmin() ?
                new String[]{"ADMIN", "USER"} :
                new String[]{"USER"};

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
