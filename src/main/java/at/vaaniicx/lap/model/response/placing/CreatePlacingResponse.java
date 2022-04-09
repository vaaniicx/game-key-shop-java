package at.vaaniicx.lap.model.response.placing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePlacingResponse {

    @JsonProperty("placing_id")
    private Long placingId;

    @JsonProperty("placing_date")
    private Instant placingDate;

    @JsonProperty("person_id")
    private Long personId;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("placing_details")
    private List<PlacingDetailsResponse> placingDetails;
}
