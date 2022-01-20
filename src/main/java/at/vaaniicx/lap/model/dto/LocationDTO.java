package at.vaaniicx.lap.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("postal")
    private String postal;

    @JsonProperty("location")
    private String location;

    @JsonProperty("country")
    private CountryDTO country;
}
