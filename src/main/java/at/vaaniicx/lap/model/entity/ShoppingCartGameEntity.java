package at.vaaniicx.lap.model.entity;

import at.vaaniicx.lap.model.entity.pk.ShoppingCartGamePk;
import lombok.*;

import javax.persistence.*;

@Entity(name = "shopping_cart_game")
@Getter
@Setter
@NoArgsConstructor
public class ShoppingCartGameEntity {

    @EmbeddedId
    private ShoppingCartGamePk id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @MapsId("shoppingCartId")
    private ShoppingCartEntity shoppingCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @MapsId("gameId")
    private GameEntity game;

    @Column(name = "amount", nullable = false)
    private byte amount;
}
