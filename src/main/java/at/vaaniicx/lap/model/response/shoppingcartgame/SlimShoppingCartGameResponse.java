package at.vaaniicx.lap.model.response.shoppingcartgame;

import at.vaaniicx.lap.model.response.game.SlimGameResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SlimShoppingCartGameResponse {

    @JsonProperty("game")
    private SlimGameResponse game;

    @JsonProperty("amount")
    private byte amount;
}
