package at.vaaniicx.lap.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyCodeDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("game")
    private GameDTO game;

    @JsonProperty("person")
    private PersonDTO person;

    @JsonProperty("key_code")
    private String keyCode;

    @JsonProperty("sold")
    private boolean sold;
}
