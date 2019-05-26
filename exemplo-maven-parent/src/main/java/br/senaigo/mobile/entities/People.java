package br.senaigo.mobile.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class People extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idPeople;
    private String name;
    private String surname;
    private String email;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> orders;
}
