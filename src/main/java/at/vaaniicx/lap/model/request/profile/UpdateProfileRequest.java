package at.vaaniicx.lap.model.request.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateProfileRequest {

    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("birth_date")
    private Date birthDate;

    private String street;

    @JsonProperty("house_number")
    private String houseNumber;

    private String door;

    private String stair;

    private String postal;

    private String location;

    @JsonProperty("country_id")
    private Long countryId;

    @JsonProperty("profile_picture")
    private byte[] profilePicture;

    @JsonProperty("role_id")
    private Long roleId;

    @JsonProperty("active")
    private boolean isActive;
}
