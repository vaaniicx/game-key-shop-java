package at.vaaniicx.lap.model.response.placingdetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlacingDetailsResponse {

    @JsonProperty("placing_id")
    private Long placingId;

    private String title;

    @JsonProperty("age_restriction")
    private byte ageRestriction;

    @JsonProperty("key_id")
    private Long keyId;

    @JsonProperty("key_code")
    private String keyCode;

    @JsonProperty("game_id")
    private Long gameId;

    private double price;
}
