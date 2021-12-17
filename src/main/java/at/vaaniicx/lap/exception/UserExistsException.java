package at.vaaniicx.lap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityExistsException;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Benutzer wurde bereits mit dieser E-Mail registriert!")
public class UserExistsException extends EntityExistsException {
}
