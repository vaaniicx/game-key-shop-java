package at.vaaniicx.lap.model.response.management;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseEntranceDataResponse {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("price")
    private double price;
}
