package at.vaaniicx.lap.model.response.shoppingcart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartGameResponse {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("thumb")
    private byte[] thumb;

    @JsonProperty("price")
    private double price;

    @JsonProperty("amount")
    private byte amount;
}
