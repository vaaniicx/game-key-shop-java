package at.vaaniicx.lap.model.response.placing;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlacingResponse {

    private Long id;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("placing_date")
    private Instant placingDate;

    @JsonProperty("placing_details")
    private List<PlacingDetailsResponse> placingDetailsResponses;

}
