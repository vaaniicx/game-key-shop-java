package at.vaaniicx.lap.model.response.management.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCategoryResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("category")
    private String category;

    @JsonProperty("description")
    private String description;
}
