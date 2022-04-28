package at.vaaniicx.lap.model.request.management.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UpdateGameRequest {

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

    @JsonProperty("system_requirements")
    private String systemRequirements;

    @JsonProperty("developer_id")
    private Long developerId;

    @JsonProperty("publisher_id")
    private Long publisherId;

    @JsonProperty("game_pictures")
    private Set<byte[]> gamePictures;

    @JsonProperty("age_restriction")
    private byte ageRestriction;

    @JsonProperty("thumbnail")
    private byte[] thumbnail;

    @JsonProperty("categories")
    private Set<Long> categories;
}
