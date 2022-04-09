package at.vaaniicx.lap.model.response.gamepicture;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GamePictureResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("bytes")
    private byte[] image;

    @JsonProperty("is_thumb")
    private boolean isThumb;
}
