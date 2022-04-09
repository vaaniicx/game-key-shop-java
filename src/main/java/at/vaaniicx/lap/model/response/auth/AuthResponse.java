package at.vaaniicx.lap.model.response.auth;

import at.vaaniicx.lap.model.response.user.UserResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    @JsonProperty("user")
    private UserResponse user;
}
