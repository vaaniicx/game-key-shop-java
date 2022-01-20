package at.vaaniicx.lap.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryRequest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("category")
    private String category;

    @JsonProperty("description")
    private String description;
}
