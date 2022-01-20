package at.vaaniicx.lap.model.request.management.publisher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterPublisherRequest {

    @JsonProperty("publisher")
    private String publisher;
}
