package at.vaaniicx.lap.model.response.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCodeResponse {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("key_id")
    private Long keyId;

    @JsonProperty("key_code")
    private String keyCode;
}
