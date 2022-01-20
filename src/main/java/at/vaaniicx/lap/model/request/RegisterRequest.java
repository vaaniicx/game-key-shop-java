package at.vaaniicx.lap.model.request;

import at.vaaniicx.lap.model.entity.RoleEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class RegisterRequest {

    @NotNull
    @JsonProperty("first_name")
    private String firstName;

    @NotNull
    @JsonProperty("last_name")
    private String lastName;

    @NotNull
    @JsonProperty("birth_date")
    private Date birthDate;

    @NotNull
    private String email;

    @NotNull
    private String street;

    @NotNull
    @JsonProperty("house_number")
    private String houseNumber;

    private String door;

    private String stair;

    @NotNull
    private String postal;

    @NotNull
    private String location;

    @NotNull
    @JsonProperty("country_id")
    private Long countryId;

    @NotNull
    private String password;

    private RoleEntity role;
}
