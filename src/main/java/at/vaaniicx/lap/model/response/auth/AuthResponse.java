package at.vaaniicx.lap.model.response.auth;

import at.vaaniicx.lap.model.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    @JsonProperty("user")
    private UserDTO user;
}
