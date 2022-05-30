package sales.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Integer id;
    private String cpf;
    private String clientName;
    private BigDecimal total;
    private String orderDate;
    private String status;
    private List<OrderItemResponse> items;
}
