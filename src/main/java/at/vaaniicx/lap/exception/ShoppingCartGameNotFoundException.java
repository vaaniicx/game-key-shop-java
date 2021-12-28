package at.vaaniicx.lap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "ShoppingCartGame wurde nicht in der Datenbank gefunden!")
public class ShoppingCartGameNotFoundException extends EntityNotFoundException {
}
