package at.vaaniicx.lap.model.response.user;

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
public class SlimUserResponse {

    private Long id;

    private String email;

    private boolean active;

    @JsonProperty("registration_date")
    private Instant registrationDate;

    @JsonProperty("last_login")
    private Instant lastLogin;

    @JsonProperty("person_id")
    private Long personId;

    @JsonProperty("role_id")
    private Long roleId;

    @JsonProperty("profile_picture")
    private byte[] profilePicture;
}
