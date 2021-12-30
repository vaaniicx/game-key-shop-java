package at.vaaniicx.lap.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherManagementDataResponse {

    @JsonProperty("publisher_id")
    private Long publisherId;

    @JsonProperty("publisher")
    private String publisher;
}
