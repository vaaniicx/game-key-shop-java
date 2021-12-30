package at.vaaniicx.lap.model.response.management;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryManagementDataResponse {

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("category")
    private String category;

    @JsonProperty("description")
    private String description;
}
