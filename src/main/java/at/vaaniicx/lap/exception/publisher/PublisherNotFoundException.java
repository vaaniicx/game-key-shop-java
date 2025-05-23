package at.vaaniicx.lap.exception.publisher;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Publisher wurde nicht in der Datenbank gefunden!")
public class PublisherNotFoundException extends EntityNotFoundException {
}
