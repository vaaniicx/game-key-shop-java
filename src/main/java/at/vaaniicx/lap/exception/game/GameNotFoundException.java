package at.vaaniicx.lap.exception.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Spiel wurde nicht in der Datenbank gefunden!")
public class GameNotFoundException extends EntityNotFoundException {
}
