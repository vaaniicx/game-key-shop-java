package at.vaaniicx.lap.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlacingDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("placing_date")
    private Instant placingDate;

    @JsonProperty("placing_details")
    private List<PlacingDetailsDTO> placingDetails;
}
