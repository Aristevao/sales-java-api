package sales.domain.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequest {

    private Integer cliente;

    private BigDecimal total;

    private List<OrderItemRequest> itens;
}
