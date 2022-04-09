package at.vaaniicx.lap.model.response.address;

import at.vaaniicx.lap.model.response.location.LocationResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private Long id;

    private LocationResponse location;

    private String street;

    @JsonProperty("house_number")
    private String houseNumber;

    private String door;

    private String stair;

}
