package at.vaaniicx.lap.model.request.management.developer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateDeveloperRequest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("developer")
    private String developer;
}
