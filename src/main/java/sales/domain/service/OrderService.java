package sales.domain.service;

import org.springframework.stereotype.Service;
import sales.domain.dto.request.OrderRequest;
import sales.domain.dto.request.UpdateOrderStatusRequest;
import sales.domain.entity.Order;
import sales.domain.enums.OrderStatus;

import java.util.Optional;

@Service
public interface OrderService {

    Order saveOrder(OrderRequest orderRequest);

    Optional<Order> getOrderById(Integer id);

    void updateOrderStatus(Integer id, OrderStatus orderStatus);
}
