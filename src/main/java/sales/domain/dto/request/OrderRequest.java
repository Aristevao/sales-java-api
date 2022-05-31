package sales.domain.dto.request;

import lombok.Data;
import sales.common.annotation.NotEmptyList;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequest {

    @NotNull(message = "Client must be provided")
    private Integer client;

    @NotNull(message = "Total must not be null")
    @Min(value = 0, message = "Total cannot be a negative value")
    @Max(value = 9999999, message = "Total must be less than 9999999")
    private BigDecimal total;

    @NotEmptyList(message = "Order must have at least one item")
    private List<OrderItemRequest> items;
}
