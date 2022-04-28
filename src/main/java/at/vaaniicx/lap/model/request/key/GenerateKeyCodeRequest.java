package at.vaaniicx.lap.model.request.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenerateKeyCodeRequest {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("amount")
    private byte amount;
}
