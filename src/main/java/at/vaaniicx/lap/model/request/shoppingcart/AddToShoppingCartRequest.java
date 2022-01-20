package at.vaaniicx.lap.model.request.shoppingcart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddToShoppingCartRequest {

    @JsonProperty("person_id")
    private Long personId;

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("amount")
    private byte amount;
}
