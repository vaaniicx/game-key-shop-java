package at.vaaniicx.lap.exception.key;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Schlüssel für dieses Spiel sind ausverkauft!")
public class NoKeysAvailableException extends RuntimeException {
}
