package at.vaaniicx.lap.model.response.management.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GamesByCategoryResponse {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("title")
    private String title;
}
