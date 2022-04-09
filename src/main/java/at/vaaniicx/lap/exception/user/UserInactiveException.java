package at.vaaniicx.lap.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Benutzerkonto wurde deaktiviert!")
public class UserInactiveException extends EntityNotFoundException {
}
