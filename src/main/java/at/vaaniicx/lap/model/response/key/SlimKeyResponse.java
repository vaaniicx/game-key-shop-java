package at.vaaniicx.lap.model.response.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlimKeyResponse {

    private Long id;

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("key_code")
    private String keyCode;

    private boolean sold;
}
