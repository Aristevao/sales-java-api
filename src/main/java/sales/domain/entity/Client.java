package sales.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "{required.name}")
    @Column(name = "name", length = 100)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Order> orders;

    @NotEmpty(message = "{required.cpf}")
    @CPF(message = "{invalid.cpf}")
    @Column(length = 11)
    private String cpf;
}