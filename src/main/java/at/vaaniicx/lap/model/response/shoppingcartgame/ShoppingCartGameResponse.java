package at.vaaniicx.lap.model.response.shoppingcartgame;

import at.vaaniicx.lap.model.response.game.GameResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartGameResponse {

    @JsonProperty("id")
    private Long shoppingCartId;

    @JsonProperty("game")
    private GameResponse game;

    @JsonProperty("amount")
    private byte amount;
}
