package at.vaaniicx.lap.model.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ShoppingCartGamePk implements Serializable {

    @Column(name = "shopping_cart_id")
    private Long shoppingCartId;

    @Column(name = "game_id")
    private Long gameId;
}
