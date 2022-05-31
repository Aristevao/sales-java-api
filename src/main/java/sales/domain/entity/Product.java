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

    @NotEmpty(message = "Description must be provided")
    @Column(name = "description")
    private String description;

    @Min(value = 0, message = "Price cannot be a negative value")
    @Max(value = 9999999, message = "Price must be less than 9999999")
    @NotNull(message = "Price must not be null")
    @Column(name = "unit_price")
    private BigDecimal price;
}