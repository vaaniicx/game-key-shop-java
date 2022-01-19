package at.vaaniicx.lap.model.response.shoppingcart;

import at.vaaniicx.lap.model.entity.ShoppingCartGameEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddToShoppingCartResponse {

    @JsonProperty("cart_id")
    private Long shoppingCartId;

    @JsonProperty("person_id")
    private Long personId;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("cart_games")
    private List<ShoppingCartGameEntity> shoppingCartGames;
}
