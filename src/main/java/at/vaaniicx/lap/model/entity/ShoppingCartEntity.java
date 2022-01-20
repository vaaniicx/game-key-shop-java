package at.vaaniicx.lap.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "shopping_cart")
@Getter
@Setter
@NoArgsConstructor
public class ShoppingCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @OneToMany(
            mappedBy = "shoppingCart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<ShoppingCartGameEntity> games;

    public ShoppingCartEntity(PersonEntity person, double totalPrice) {
        this.person = person;
        this.totalPrice = totalPrice;
    }
}
