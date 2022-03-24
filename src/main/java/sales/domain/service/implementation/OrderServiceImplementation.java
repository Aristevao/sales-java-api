package sales.domain.service.implementation;

import org.springframework.stereotype.Service;
import sales.domain.repository.PedidosRepository;
import sales.domain.service.OrderService;

@Service
public class OrderServiceImplementation implements OrderService {

    private PedidosRepository orderRepository;
}
