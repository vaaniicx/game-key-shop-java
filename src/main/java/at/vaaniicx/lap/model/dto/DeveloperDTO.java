package at.vaaniicx.lap.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String developer;
}
