package at.vaaniicx.lap.model.response.management.developer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse {

    @JsonProperty("developer_id")
    private Long developerId;

    @JsonProperty("developer")
    private String developer;
}
