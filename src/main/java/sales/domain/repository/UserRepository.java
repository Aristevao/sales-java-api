package sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}