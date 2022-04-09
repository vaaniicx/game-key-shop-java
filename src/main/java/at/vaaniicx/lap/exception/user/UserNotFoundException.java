package at.vaaniicx.lap.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Benutzer wurde nicht in der Datenbank gefunden!")
public class UserNotFoundException extends EntityNotFoundException {
}
