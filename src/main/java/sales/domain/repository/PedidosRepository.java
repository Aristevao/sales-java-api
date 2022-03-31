package sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.domain.entity.Order;

public interface PedidosRepository extends JpaRepository<Order, Integer> {

//    List<Pedido> findByCliente(Client client);
//
//    @Query(" select p from Pedido p left join fetch p.itens where p.id = :id ")
//    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}