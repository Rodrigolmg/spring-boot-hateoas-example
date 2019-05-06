package br.senaigo.mobile.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@Builder
@Table(name = "[order]")
public class Order extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idOrder;
    private UUID uuid;

    @OneToOne
    private People people = new People();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<Product>();

    private BigDecimal sumOrders(){
        return productList.stream().map(Product::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
