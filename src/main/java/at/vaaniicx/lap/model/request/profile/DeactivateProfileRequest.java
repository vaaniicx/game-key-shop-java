package at.vaaniicx.lap.model.request.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DeactivateProfileRequest {

    @JsonProperty("user_id")
    private Long userId;
}
