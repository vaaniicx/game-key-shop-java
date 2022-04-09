package at.vaaniicx.lap.model.response.profilepicture;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfilePictureResponse {

    private Long id;

    @JsonProperty("bytes")
    private byte[] picture;
}
