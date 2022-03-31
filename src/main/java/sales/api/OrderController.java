package sales.api;

import org.springframework.web.bind.annotation.*;
import sales.domain.dto.request.OrderRequest;
import sales.domain.service.OrderService;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void save(@RequestBody OrderRequest orderRequest) {
        orderService.saveOrder(orderRequest);
    }
}
