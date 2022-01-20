package at.vaaniicx.lap.model.request.management.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCodeRequest {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("key_code")
    private String keyCode;
}
