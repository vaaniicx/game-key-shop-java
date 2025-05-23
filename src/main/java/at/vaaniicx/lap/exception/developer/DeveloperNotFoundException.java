package at.vaaniicx.lap.exception.developer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entwickler wurde nicht in der Datenbank gefunden!")
public class DeveloperNotFoundException extends EntityNotFoundException {
}
