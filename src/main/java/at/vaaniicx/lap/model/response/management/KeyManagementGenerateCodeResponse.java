package at.vaaniicx.lap.model.response.management;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyManagementGenerateCodeResponse {

    @JsonProperty("key_id")
    private Long keyId;

    @JsonProperty("key_code")
    private String keyCode;
}
