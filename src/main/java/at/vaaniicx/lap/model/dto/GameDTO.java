package at.vaaniicx.lap.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {

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
    private Long developerId;

    @JsonProperty("age_restriction")
    private byte ageRestriction;

    @JsonProperty("encoded_thumb")
    private byte[] encodedThumb;
}
