package sales.api;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sales.domain.dto.request.OrderRequest;
import sales.domain.dto.request.UpdateOrderStatusRequest;
import sales.domain.dto.response.OrderItemResponse;
import sales.domain.dto.response.OrderResponse;
import sales.domain.entity.Order;
import sales.domain.entity.OrderItem;
import sales.domain.enums.OrderStatus;
import sales.domain.service.OrderService;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody @Valid OrderRequest orderRequest) {
        Order order = orderService.saveOrder(orderRequest);
        return order.getId();
    }

    @GetMapping("{id}")
    public OrderResponse findOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Order not found: " + id));
    }

    @PatchMapping("{orderId}")
    @ResponseStatus(NO_CONTENT)
    public void updateOderStatus(@PathVariable Integer orderId, @RequestBody UpdateOrderStatusRequest orderStatus) {
        orderService.updateOrderStatus(orderId, OrderStatus.valueOf(orderStatus.getStatus()));
    }

    private OrderResponse mapToDto(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                .cpf(order.getClient().getCpf())
                .clientName(order.getClient().getName())
                .total(order.getTotal())
                .status(order.getOrderStatus().name())
                .items(mapOrderItemsToDto(order.getOrderItems()))
                .build();
    }

    private List<OrderItemResponse> mapOrderItemsToDto(List<OrderItem> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream().map(
                item -> OrderItemResponse.builder()
                        .description(item.getProduct().getDescription())
                        .price(item.getProduct().getPrice())
                        .quantity(item.getQuantity())
                        .build()
        ).collect(Collectors.toList());
    }
}
