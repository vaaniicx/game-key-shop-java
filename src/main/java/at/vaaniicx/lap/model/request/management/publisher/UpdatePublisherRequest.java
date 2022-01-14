package at.vaaniicx.lap.model.request.management.publisher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdatePublisherRequest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("publisher")
    private String publisher;
}
