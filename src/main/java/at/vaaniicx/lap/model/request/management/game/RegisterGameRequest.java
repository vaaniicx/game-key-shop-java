package at.vaaniicx.lap.model.request.management.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RegisterGameRequest {

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
    private List<byte[]> gamePictures;

    @JsonProperty("age_restriction")
    private byte ageRestriction;

    @JsonProperty("thumbnail")
    private byte[] thumbnail;

    @JsonProperty("categories")
    private List<Long> categories;
}
