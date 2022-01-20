package at.vaaniicx.lap.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {

    private Long id;

    @JsonProperty("personId")
    private Long personId;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("shopping_cart_game")
    private List<ShoppingCartGameDTO> shoppingCartGames;
}
