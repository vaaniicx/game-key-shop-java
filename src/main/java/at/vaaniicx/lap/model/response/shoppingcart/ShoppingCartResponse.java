package at.vaaniicx.lap.model.response.shoppingcart;

import at.vaaniicx.lap.model.response.shoppingcartgame.ShoppingCartGameResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartResponse {

    @JsonProperty("id")
    private Long shoppingCartId;

    @JsonProperty("person_id")
    private Long personId;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("games")
    private List<ShoppingCartGameResponse> shoppingCartGames;
}
