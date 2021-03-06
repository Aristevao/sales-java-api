package sales.domain.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sales.domain.dto.request.OrderItemRequest;
import sales.domain.dto.request.OrderRequest;
import sales.domain.entity.Client;
import sales.domain.entity.Order;
import sales.domain.entity.OrderItem;
import sales.domain.entity.Product;
import sales.domain.enums.OrderStatus;
import sales.domain.repository.ClientRepository;
import sales.domain.repository.OrderItemRepository;
import sales.domain.repository.OrderRepository;
import sales.domain.repository.ProductRepository;
import sales.domain.service.OrderService;
import sales.common.exception.BusinessLogicException;
import sales.common.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {

    private final OrderRepository orderRepository;

    private final ClientRepository clientRepository;

    private final ProductRepository productRepository;

    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    // This method has more than one operation in the database (save/saveAll). If one of the operations does not succeed, the annotation runs rollback and none changes are made.
    public Order saveOrder(OrderRequest orderRequest) {
        Integer clientId = orderRequest.getClient();
        Client client = clientRepository
                .findById(clientId)
                .orElseThrow(() -> new BusinessLogicException("Client does not exist: " + clientId));

        Order order = new Order();
        order.setTotal(orderRequest.getTotal());
        order.setOrderDate(LocalDate.now());
        order.setClient(client);
        order.setOrderStatus(OrderStatus.DONE);
        orderRepository.save(order);

        List<OrderItem> orderItems = convertItems(order, orderRequest.getItems());
        orderItemRepository.saveAll(orderItems);

        order.setOrderItems(orderItems);
        return order;
    }

    @Override
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findByIdFetchOrderItems(id);
    }

    @Override
    @Transactional
    public void updateOrderStatus(Integer id, OrderStatus orderStatus) {
        Order order = findOrderById(id);
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }

    public Order findOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found: " + id));
    }

    private List<OrderItem> convertItems(Order order, List<OrderItemRequest> items) {
        if (items.isEmpty()) {
            throw new BusinessLogicException("Is not possible to place an order without the items");
        }

        return items.stream()
                .map(dto -> {
                    Integer productId = dto.getProduct();
                    Product product = productRepository
                            .findById(productId)
                            .orElseThrow(() -> new BusinessLogicException("Product does not exist: " + productId));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(dto.getQuantity());
                    orderItem.setOrder(order);
                    orderItem.setProduct(product);
                    return orderItem;
                }).collect(Collectors.toList());
    }
}
