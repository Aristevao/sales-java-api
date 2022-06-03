package sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);
}