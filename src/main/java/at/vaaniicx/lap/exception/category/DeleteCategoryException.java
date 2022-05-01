package at.vaaniicx.lap.exception.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Kategorie kann nicht gelöscht werden, da sie in Verwendung ist!")
public class DeleteCategoryException extends RuntimeException {
}
