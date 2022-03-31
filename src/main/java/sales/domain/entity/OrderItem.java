package sales.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Pedido order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Produto product;

    @Column
    private Integer quantity;
}