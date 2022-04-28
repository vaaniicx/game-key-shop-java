package at.vaaniicx.lap.model.request.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterKeyCodeRequest {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("key_code")
    private String keyCode;
}
