package at.vaaniicx.lap.exception.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Spiel kann nicht gelöscht werden, da es in Verwendung ist!")
public class DeleteGameException extends RuntimeException {
}
