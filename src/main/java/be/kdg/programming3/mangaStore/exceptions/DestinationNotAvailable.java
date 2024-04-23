package be.kdg.programming3.mangaStore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class DestinationNotAvailable extends RuntimeException {
    public DestinationNotAvailable(String message) {
        super(message);
    }
}
