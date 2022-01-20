package at.vaaniicx.lap.model.request.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateProfileRequest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("birth_date")
    private Date birthDate;

    @JsonProperty("street")
    private String street;

    @JsonProperty("house_number")
    private String houseNumber;

    @JsonProperty("door")
    private String door;

    @JsonProperty("stair")
    private String stair;

    @JsonProperty("postal")
    private String postal;

    @JsonProperty("location")
    private String location;

    @JsonProperty("country_id")
    private Long countryId;

    @JsonProperty("profile_picture")
    private byte[] profilePicture;
}
