package at.vaaniicx.lap.model.response.management.publisher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPublisherResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("publisher")
    private String publisher;
}
