package sales.domain.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "{required.description}")
    @Column(name = "description")
    private String description;

    @Min(value = 0, message = "{negative.price}")
    @Max(value = 9999999, message = "{exaggerated.price}")
    @NotNull(message = "{required.price}")
    @Column(name = "price")
    private BigDecimal price;
}