package at.vaaniicx.lap.model.response.management;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleManagementDataResponse {

    @JsonProperty("id")
    private Long roleId;

    @JsonProperty("role")
    private String role;
}
