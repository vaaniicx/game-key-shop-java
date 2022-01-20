package at.vaaniicx.lap.model.request.management.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRoleRequest {

    @JsonProperty("role")
    private String role;
}
