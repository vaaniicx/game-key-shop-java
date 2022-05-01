package at.vaaniicx.lap.model.request.publisher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterPublisherRequest {

    @JsonProperty("publisher")
    private String publisher;
}
