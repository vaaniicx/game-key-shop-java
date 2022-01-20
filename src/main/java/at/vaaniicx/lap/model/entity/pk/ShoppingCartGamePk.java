package at.vaaniicx.lap.model.entity.pk;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartGamePk implements Serializable {

    @Column(name = "shopping_cart_id")
    private Long shoppingCartId;

    @Column(name = "game_id")
    private Long gameId;
}
