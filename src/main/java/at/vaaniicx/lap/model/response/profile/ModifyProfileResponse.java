package at.vaaniicx.lap.model.response.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModifyProfileResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("email")
    private String email;
}
