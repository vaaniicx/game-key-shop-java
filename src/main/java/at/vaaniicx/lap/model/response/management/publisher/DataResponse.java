package at.vaaniicx.lap.model.response.management.publisher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse {

    @JsonProperty("publisher_id")
    private Long publisherId;

    @JsonProperty("publisher")
    private String publisher;
}
