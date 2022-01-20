package at.vaaniicx.lap.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfilePictureDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("bytes")
    private byte[] picture;
}
