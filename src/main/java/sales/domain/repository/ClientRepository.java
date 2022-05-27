package sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.domain.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}