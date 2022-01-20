package at.vaaniicx.lap.model.response.management.placement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlacingDetailsResponse {

    @JsonProperty("placing_id")
    private Long placingId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("age_restriction")
    private byte ageRestriction;

    @JsonProperty("key_id")
    private Long keyId;

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("key_code")
    private String keyCode;
}
