package at.vaaniicx.lap.model.response.person;

import at.vaaniicx.lap.model.response.address.AddressResponse;
import at.vaaniicx.lap.model.response.placing.PlacingResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {

    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("birth_date")
    private Date birthDate;

    private AddressResponse address;

    private List<PlacingResponse> placings;

}
