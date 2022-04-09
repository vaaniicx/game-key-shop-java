package at.vaaniicx.lap.model.response.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse {

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
