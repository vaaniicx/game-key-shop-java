package at.vaaniicx.lap.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class JwtLoginRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;
}
