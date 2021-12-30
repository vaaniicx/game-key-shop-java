package at.vaaniicx.lap.model.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {

    private String email;

    @JsonProperty("registration_date")
    private Instant registrationDate;
}
