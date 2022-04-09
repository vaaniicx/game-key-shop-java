package at.vaaniicx.lap.model.response.location;

import at.vaaniicx.lap.model.response.country.CountryResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponse {

    private Long id;

    private String postal;

    private String location;

    private CountryResponse country;

}
