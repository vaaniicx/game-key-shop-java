package at.vaaniicx.lap.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartGameDTO {

    @JsonProperty("id")
    private Long shoppingCartId;

    @JsonProperty("game")
    private GameDTO game;

    @JsonProperty("amount")
    private byte amount;
}
