package at.vaaniicx.lap.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtLoginResponse {

    private String token;
}
