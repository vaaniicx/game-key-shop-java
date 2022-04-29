package at.vaaniicx.lap.model.response.shoppingcart;

import at.vaaniicx.lap.model.response.shoppingcartgame.SlimShoppingCartGameResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SlimShoppingCartResponse {

    @JsonProperty("id")
    private Long shoppingCartId;

    @JsonProperty("person_id")
    private Long personId;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("games")
    private List<SlimShoppingCartGameResponse> shoppingCartGames;
}
