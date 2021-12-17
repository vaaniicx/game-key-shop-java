package at.vaaniicx.lap.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class JwtLoginRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;
}
