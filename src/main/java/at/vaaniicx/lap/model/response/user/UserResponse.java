package at.vaaniicx.lap.model.response.user;

import at.vaaniicx.lap.model.response.person.PersonResponse;
import at.vaaniicx.lap.model.response.role.RoleResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String email;

    private boolean active;

    @JsonProperty("registration_date")
    private Instant registrationDate;

    @JsonProperty("last_login")
    private Instant lastLogin;

    private PersonResponse person;

    private RoleResponse role;

    @JsonProperty("profile_picture")
    private byte[] profilePicture;

}
