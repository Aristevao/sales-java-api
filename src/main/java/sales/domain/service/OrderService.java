package sales.domain.service;

import org.springframework.stereotype.Service;
import sales.domain.dto.request.OrderRequest;
import sales.domain.entity.Order;

@Service
public interface OrderService {

    Order saveOrder(OrderRequest orderRequest);
}
