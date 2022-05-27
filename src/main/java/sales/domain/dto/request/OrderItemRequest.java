package sales.domain.dto.request;

import lombok.Data;

@Data
public class OrderItemRequest {

    private Integer product;

    private Integer quantity;
}
