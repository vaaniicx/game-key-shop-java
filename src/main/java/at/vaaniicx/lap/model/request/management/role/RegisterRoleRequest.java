package at.vaaniicx.lap.model.request.management.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterRoleRequest {

    @JsonProperty("role")
    private String role;
}
