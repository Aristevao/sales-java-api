package sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sales.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}