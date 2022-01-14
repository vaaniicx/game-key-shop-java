package at.vaaniicx.lap.model.request.management.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterCategoryRequest {

    @JsonProperty("category")
    private String category;

    @JsonProperty("description")
    private String description;
}
