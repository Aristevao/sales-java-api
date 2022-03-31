package sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.domain.entity.OrderItem;

public interface ItemsPedidoRepository extends JpaRepository<OrderItem, Integer> {
}