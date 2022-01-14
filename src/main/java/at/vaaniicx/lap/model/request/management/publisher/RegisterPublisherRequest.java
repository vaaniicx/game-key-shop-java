package at.vaaniicx.lap.model.request.management.publisher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterPublisherRequest {

    @JsonProperty("publisher")
    private String publisher;
}
