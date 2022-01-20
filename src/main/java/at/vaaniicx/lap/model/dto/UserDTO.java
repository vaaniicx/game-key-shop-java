package at.vaaniicx.lap.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("registration_date")
    private Instant registrationDate;

    @JsonProperty("last_login")
    private Instant lastLogin;

    @JsonProperty("person")
    private PersonDTO person;

    @JsonProperty("role")
    private RoleDTO role;

    @JsonProperty("profile_picture")
    private byte[] profilePicture;
}
