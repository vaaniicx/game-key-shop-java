package at.vaaniicx.lap.model.response.developer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse {

    @JsonProperty("developer_id")
    private Long developerId;

    @JsonProperty("developer")
    private String developer;
}
