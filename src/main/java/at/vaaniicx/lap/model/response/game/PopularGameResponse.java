package at.vaaniicx.lap.model.response.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PopularGameResponse {

    private Long id;

    private String title;

    @JsonProperty("keys_sold")
    private Long keysSold;
}
