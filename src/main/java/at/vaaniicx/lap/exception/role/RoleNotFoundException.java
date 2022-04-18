package at.vaaniicx.lap.exception.role;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Rolle wurde nicht in der Datenbank gefunden!")
public class RoleNotFoundException extends EntityNotFoundException {
}
