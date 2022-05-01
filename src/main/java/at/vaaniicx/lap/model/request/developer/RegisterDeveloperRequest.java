package at.vaaniicx.lap.model.request.developer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDeveloperRequest {

    @JsonProperty("developer")
    private String developer;
}
