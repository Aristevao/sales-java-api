package sales.domain.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sales.domain.dto.request.OrderItemRequest;
import sales.domain.dto.request.OrderRequest;
import sales.domain.entity.Client;
import sales.domain.entity.ItemPedido;
import sales.domain.entity.Pedido;
import sales.domain.entity.Produto;
import sales.domain.repository.ClientesRepository;
import sales.domain.repository.ItemsPedidoRepository;
import sales.domain.repository.PedidosRepository;
import sales.domain.repository.ProductRepository;
import sales.domain.service.OrderService;
import sales.exception.BusinessLogicException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {

    private final PedidosRepository orderRepository;

    private final ClientesRepository clientesRepository;

    private final ProductRepository productRepository;

    private final ItemsPedidoRepository orderItemRepository;

    @Override
    @Transactional
    // This method has more than one operation in the database (save/saveAll). If one of the operations does not succeed, the annotation runs rollback and none changes are made.
    public Pedido saveOrder(OrderRequest orderRequest) {
        Integer clientId = orderRequest.getCliente();
        Client client = clientesRepository
                .findById(clientId)
                .orElseThrow(() -> new BusinessLogicException("Client does not exist: " + clientId));

        Pedido order = new Pedido();
        order.setTotal(orderRequest.getTotal());
        order.setDataPedido(LocalDate.now());
        order.setClient(client);

        List<ItemPedido> orderItems = convertItems(order, orderRequest.getItens());
        order.setItens(orderItems);

        Pedido savedOrder = orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);
        return savedOrder;
    }

    private List<ItemPedido> convertItems(Pedido order, List<OrderItemRequest> items) {
        if (items.isEmpty()) {
            throw new BusinessLogicException("Is not possible to place an order without the items");
        }

        return items.stream()
                .map(dto -> {
                    Integer productId = dto.getProduto();
                    Produto product = productRepository
                            .findById(productId)
                            .orElseThrow(() -> new BusinessLogicException("Product does not exist: " + productId));

                    ItemPedido orderItem = new ItemPedido();
                    orderItem.setQuantidade(dto.getQuantidade());
                    orderItem.setPedido(order);
                    orderItem.setProduto(product);
                    return orderItem;
                }).collect(Collectors.toList());
    }
}
