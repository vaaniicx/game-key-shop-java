package at.vaaniicx.lap.model.response.management;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyManagementDataResponse {

    @JsonProperty("key_id")
    private Long keyId;

    @JsonProperty("key_code")
    private String keyCode;

    @JsonProperty("sold")
    private boolean sold;

    @JsonProperty("user_id")
    private Long user_id;

    @JsonProperty("email")
    private String email;
}
