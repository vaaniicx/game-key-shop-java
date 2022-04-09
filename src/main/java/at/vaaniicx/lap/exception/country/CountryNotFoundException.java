package at.vaaniicx.lap.exception.country;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Land wurde nicht in der Datenbank gefunden!")
public class CountryNotFoundException extends EntityNotFoundException {
}
