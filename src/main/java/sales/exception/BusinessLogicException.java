package sales.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class BusinessLogicException extends RuntimeException {
    public BusinessLogicException(final String message) {
        super(message);
    }
}
