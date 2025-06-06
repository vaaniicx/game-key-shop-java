package at.vaaniicx.lap.model.response.management;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressManagementDataResponse {

    @JsonProperty("address_id")
    private Long addressId;

    @JsonProperty("street")
    private String street;

    @JsonProperty("house_number")
    private String houseNumber;

    @JsonProperty("stair")
    private String stair;

    @JsonProperty("door")
    private String door;

    @JsonProperty("location_id")
    private Long locationId;

    @JsonProperty("location")
    private String location;

    @JsonProperty("postal")
    private String postal;

    @JsonProperty("country_id")
    private Long countryId;

    @JsonProperty("country")
    private String country;
}
