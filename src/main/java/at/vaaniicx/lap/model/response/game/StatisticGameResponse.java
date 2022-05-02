package at.vaaniicx.lap.model.response.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatisticGameResponse {

    private Long id;

    private String title;

    @JsonProperty("keys_sold")
    private Long keysSold;
}
