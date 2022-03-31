package sales.domain.service;

import org.springframework.stereotype.Service;
import sales.domain.dto.request.OrderRequest;
import sales.domain.entity.Pedido;

@Service
public interface OrderService {

    Pedido saveOrder(OrderRequest orderRequest);
}
