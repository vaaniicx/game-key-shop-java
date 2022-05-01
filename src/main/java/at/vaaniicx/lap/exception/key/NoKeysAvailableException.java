package at.vaaniicx.lap.exception.key;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Für dieses Spiel gibt es keine verfügbaren Schlüssel!")
public class NoKeysAvailableException extends RuntimeException {
}
