package at.vaaniicx.lap.model.request.management.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterCodeRequest {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("key_code")
    private String keyCode;
}
