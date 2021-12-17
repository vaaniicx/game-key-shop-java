package at.vaaniicx.lap.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "shopping_cart")
@Data
@NoArgsConstructor
public class ShoppingCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;
}
