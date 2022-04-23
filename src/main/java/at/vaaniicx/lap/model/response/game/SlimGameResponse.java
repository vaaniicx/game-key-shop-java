package at.vaaniicx.lap.model.response.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlimGameResponse {

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

    @JsonProperty("developer_id")
    private Long developer;

    @JsonProperty("publisher_id")
    private Long publisher;

    @JsonProperty("game_picture_ids")
    private List<Long> gamePictureIds;

    @JsonProperty("age_restriction")
    private byte ageRestriction;

    @JsonProperty("thumbnail_id")
    private Long thumbnailId;

    @JsonProperty("category_ids")
    private List<Long> categories;

    @JsonProperty("keys_sold")
    private Long keysSold;

    @JsonProperty("keys_avail")
    private Long keysAvail;
}
