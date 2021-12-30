package at.vaaniicx.lap.model.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtLoginResponse {

    private String token;
}
