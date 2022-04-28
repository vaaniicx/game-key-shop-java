package at.vaaniicx.lap.model.request.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateKeyCodeRequest {

    private Long id;

    @JsonProperty("key_code")
    private String keyCode;
}
