package at.vaaniicx.lap.model.request.management.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCategoryRequest {

    @JsonProperty("category")
    private String category;

    @JsonProperty("description")
    private String description;
}
