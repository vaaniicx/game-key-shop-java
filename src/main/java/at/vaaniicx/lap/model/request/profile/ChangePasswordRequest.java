package at.vaaniicx.lap.model.request.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("current_password")
    private String currentPassword;

    @JsonProperty("new_password")
    private String newPassword;

    @JsonProperty("new_password_repeated")
    private String newPasswordRepeated;
}
