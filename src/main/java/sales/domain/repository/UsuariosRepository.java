package sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.domain.entity.Usuario;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);
}