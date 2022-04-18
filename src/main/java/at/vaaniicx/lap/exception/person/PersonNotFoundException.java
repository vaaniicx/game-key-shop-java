package at.vaaniicx.lap.exception.person;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Person wurde nicht in der Datenbank gefunden!")
public class PersonNotFoundException extends EntityNotFoundException {
}
