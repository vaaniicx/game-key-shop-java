package at.vaaniicx.lap.model.response.management.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameFlatResponse {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("keys_sold")
    private Long keysSold;

    @JsonProperty("keys_avail")
    private Long keysAvail;
}
