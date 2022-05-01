package at.vaaniicx.lap.exception.developer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Entwickler kann nicht gelöscht werden, da er in Verwendung ist!")
public class DeleteDeveloperException extends RuntimeException {
}
