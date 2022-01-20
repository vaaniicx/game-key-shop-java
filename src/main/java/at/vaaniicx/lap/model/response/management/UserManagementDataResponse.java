package at.vaaniicx.lap.model.response.management;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserManagementDataResponse {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("role_id")
    private Long roleId;

    @JsonProperty("role")
    private String role;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("registration_date")
    private Instant registrationDate;

    @JsonProperty("last_login")
    private Instant lastLogin;

}
