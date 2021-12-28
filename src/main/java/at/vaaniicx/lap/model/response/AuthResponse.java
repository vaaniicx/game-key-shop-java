package at.vaaniicx.lap.model.response;

import at.vaaniicx.lap.model.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    @JsonProperty("user")
    private UserDTO user;
}
