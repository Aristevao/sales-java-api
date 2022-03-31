package sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sales.domain.entity.Client;
import sales.domain.entity.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

//    List<Pedido> findByCliente(Client client);
//
//    @Query(" select p from Pedido p left join fetch p.itens where p.id = :id ")
//    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}