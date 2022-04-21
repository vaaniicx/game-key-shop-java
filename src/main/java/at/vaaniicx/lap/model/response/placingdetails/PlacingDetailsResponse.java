package at.vaaniicx.lap.model.response.placingdetails;

import at.vaaniicx.lap.model.response.key.KeyResponse;
import at.vaaniicx.lap.model.response.placing.PlacingResponse;
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

    @JsonProperty("key_code")
    private KeyResponse keyCode;

    @JsonProperty("price")
    private double price;

}
