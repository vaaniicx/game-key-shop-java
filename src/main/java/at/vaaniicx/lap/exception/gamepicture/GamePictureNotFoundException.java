package at.vaaniicx.lap.exception.gamepicture;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Spielbild wurde nicht in der Datenbank gefunden!")
public class GamePictureNotFoundException extends EntityNotFoundException {
}
