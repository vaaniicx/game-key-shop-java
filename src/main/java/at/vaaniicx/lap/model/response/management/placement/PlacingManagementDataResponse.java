package at.vaaniicx.lap.model.response.management.placement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlacingManagementDataResponse {

    @JsonProperty("placing_id")
    private Long placingId;

    @JsonProperty("placing_date")
    private Instant placingDate;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("person_id")
    private Long personId;

    @JsonProperty("placing_details")
    private List<PlacingDetailsResponse> placingDetails;
}
