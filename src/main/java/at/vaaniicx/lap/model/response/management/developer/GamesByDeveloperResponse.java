package at.vaaniicx.lap.model.response.management.developer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GamesByDeveloperResponse {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("age_restriction")
    private byte ageRestriction;
}
