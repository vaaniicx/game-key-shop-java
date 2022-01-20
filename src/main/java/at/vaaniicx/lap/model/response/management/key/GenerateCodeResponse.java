package at.vaaniicx.lap.model.response.management.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenerateCodeResponse {

    @JsonProperty("key_id")
    private Long keyId;

    @JsonProperty("key_code")
    private String keyCode;
}
