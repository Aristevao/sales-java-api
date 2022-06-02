package sales.domain.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "{required.description}")
    @Column(name = "description")
    private String description;

    @Min(value = 0, message = "{negative.price}")
    @Max(value = 9999999, message = "{exaggerated.price}")
    @NotNull(message = "{required.price}")
    @Column(name = "unit_price")
    private BigDecimal price;
}