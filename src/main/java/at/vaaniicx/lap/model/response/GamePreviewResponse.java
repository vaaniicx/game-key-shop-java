package at.vaaniicx.lap.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GamePreviewResponse {

    @JsonProperty("game_id")
    private Long gameId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("price")
    private double price;

    @JsonProperty("short_description")
    private String shortDescription;

    @JsonProperty("thumbnail_id")
    private Long thumbnailId;

    @JsonProperty("thumbnail")
    private byte[] thumbnail;

    @JsonProperty("available_keys")
    private Long availableKeys;
}
