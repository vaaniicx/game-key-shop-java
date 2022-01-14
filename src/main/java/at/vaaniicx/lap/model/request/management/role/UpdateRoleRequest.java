package at.vaaniicx.lap.model.request.management.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateRoleRequest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("role")
    private String role;
}
