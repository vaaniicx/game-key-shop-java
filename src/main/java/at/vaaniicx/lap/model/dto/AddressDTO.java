package at.vaaniicx.lap.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("location")
    private LocationDTO location;

    @JsonProperty("street")
    private String street;

    @JsonProperty("house_number")
    private String houseNumber;

    @JsonProperty("door")
    private String door;

    @JsonProperty("stair")
    private String stair;
}
