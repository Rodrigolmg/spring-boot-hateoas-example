package br.senaigo.mobile.entities;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import lombok.Builder;
import lombok.Data;


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
        return productList.stream().max(Product::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
