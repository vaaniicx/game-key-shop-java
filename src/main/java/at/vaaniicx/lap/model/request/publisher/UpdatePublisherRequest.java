package at.vaaniicx.lap.model.request.publisher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePublisherRequest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("publisher")
    private String publisher;
}
