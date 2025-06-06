package at.vaaniicx.lap.model.response.key;

import at.vaaniicx.lap.model.response.game.GameResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeyResponse {

    private Long id;

    private GameResponse game;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("key_code")
    private String keyCode;

    private boolean sold;
}
