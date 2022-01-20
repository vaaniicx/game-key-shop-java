package at.vaaniicx.lap.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlacingDetailsDTO {

    @JsonProperty("placing")
    private PlacingDTO placing;

    @JsonProperty("key_code")
    private KeyCodeDTO keyCode;

    @JsonProperty("price")
    private double price;
}
