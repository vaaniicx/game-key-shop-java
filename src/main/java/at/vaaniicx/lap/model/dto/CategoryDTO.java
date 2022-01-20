package at.vaaniicx.lap.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("category")
    private String category;

    @JsonProperty("description")
    private String description;
}
