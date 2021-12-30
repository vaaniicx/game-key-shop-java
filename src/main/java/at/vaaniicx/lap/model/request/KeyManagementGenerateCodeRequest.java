package at.vaaniicx.lap.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyManagementGenerateCodeRequest {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("amount")
    private byte amount;
}
