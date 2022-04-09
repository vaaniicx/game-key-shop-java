package at.vaaniicx.lap.model.response.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
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
