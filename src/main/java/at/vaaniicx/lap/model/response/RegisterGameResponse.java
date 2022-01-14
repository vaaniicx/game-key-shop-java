package at.vaaniicx.lap.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterGameResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("release_date")
    private Date releaseDate;

    @JsonProperty("developer_id")
    private Long developerId;

    @JsonProperty("publisher_id")
    private Long publisherId;

    @JsonProperty("age_restriction")
    private byte ageRestriction;
}
