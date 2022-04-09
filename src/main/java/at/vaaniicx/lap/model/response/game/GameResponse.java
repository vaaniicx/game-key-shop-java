package at.vaaniicx.lap.model.response.game;

import at.vaaniicx.lap.model.response.category.CategoryResponse;
import at.vaaniicx.lap.model.response.developer.DeveloperResponse;
import at.vaaniicx.lap.model.response.publisher.PublisherResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameResponse {

    private Long id;

    private String title;

    private String description;

    @JsonProperty("short_description")
    private String shortDescription;

    @JsonProperty("release_date")
    private Date releaseDate;

    @JsonProperty("original_price")
    private double originalPrice;

    private double price;

    private double savings;

    @JsonProperty("system_requirements")
    private String systemRequirements;

    private DeveloperResponse developer;

    private PublisherResponse publisher;

    @JsonProperty("game_pictures")
    private List<byte[]> gamePictures;

    @JsonProperty("age_restriction")
    private byte ageRestriction;

    private byte[] thumbnail;

    private List<CategoryResponse> categories;
}
