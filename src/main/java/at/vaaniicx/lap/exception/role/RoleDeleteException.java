package at.vaaniicx.lap.exception.role;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Rolle kann nicht gelöscht werden, da sie in Verwendung ist!")
public class RoleDeleteException extends RuntimeException {
}
