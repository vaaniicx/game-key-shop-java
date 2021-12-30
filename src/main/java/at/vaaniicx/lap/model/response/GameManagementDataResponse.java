package at.vaaniicx.lap.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameManagementDataResponse {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("developer_id")
    private Long developerId;

    @JsonProperty("developer")
    private String developer;

    @JsonProperty("publisher_id")
    private Long publisherId;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("release_date")
    private Date releaseDate;

    @JsonProperty("original_price")
    private double originalPrice;

    @JsonProperty("price")
    private double price;

    @JsonProperty("age_restriction")
    private byte ageRestriction;
}
