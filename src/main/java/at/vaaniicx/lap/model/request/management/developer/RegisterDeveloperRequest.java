package at.vaaniicx.lap.model.request.management.developer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterDeveloperRequest {

    @JsonProperty("developer")
    private String developer;
}
