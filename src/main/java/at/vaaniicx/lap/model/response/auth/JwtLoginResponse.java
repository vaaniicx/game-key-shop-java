package at.vaaniicx.lap.model.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtLoginResponse {

    private String token;
}
