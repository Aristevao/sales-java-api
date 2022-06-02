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

    @NotNull(message = "{required.client}")
    private Integer client;

    @NotNull(message = "{required.total}")
    @Min(value = 0, message = "{negative.total}")
    @Max(value = 9999999, message = "{exaggerated.total}")
    private BigDecimal total;

    @NotEmptyList(message = "{empty.list}")
    private List<OrderItemRequest> items;
}
