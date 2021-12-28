package at.vaaniicx.lap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Token ist nicht gültig!")
public class InvalidTokenException extends UserNotFoundException {
}
