package at.vaaniicx.lap.model.response.management;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryManagementDataResponse {

    @JsonProperty("id")
    private Long categoryId;

    @JsonProperty("category")
    private String category;

    @JsonProperty("description")
    private String description;
}
