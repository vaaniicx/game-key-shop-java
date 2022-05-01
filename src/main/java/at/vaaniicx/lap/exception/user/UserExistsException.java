package at.vaaniicx.lap.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityExistsException;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Mit dieser E-Mail existiert bereits ein Benutzerkonto!")
public class UserExistsException extends EntityExistsException {
}
