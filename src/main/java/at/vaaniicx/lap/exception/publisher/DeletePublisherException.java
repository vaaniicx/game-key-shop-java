package at.vaaniicx.lap.exception.publisher;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Publisher kann nicht gelöscht werden, da er in Verwendung ist!")
public class DeletePublisherException extends RuntimeException {
}
