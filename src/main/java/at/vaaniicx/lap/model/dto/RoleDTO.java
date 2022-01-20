package at.vaaniicx.lap.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("role")
    private String roleName;
}
