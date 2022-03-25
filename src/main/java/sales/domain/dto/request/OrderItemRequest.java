package sales.domain.dto.request;

import lombok.Data;

@Data
public class OrderItemRequest {

    private Integer produto;

    private Integer quantidade;
}
