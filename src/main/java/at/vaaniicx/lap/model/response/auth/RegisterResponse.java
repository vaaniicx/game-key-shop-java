package at.vaaniicx.lap.model.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {

    private String email;

    @JsonProperty("registration_date")
    private Instant registrationDate;
}
