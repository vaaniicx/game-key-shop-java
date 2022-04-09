package at.vaaniicx.lap.model.response.developer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDeveloperResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("developer")
    private String developer;
}
