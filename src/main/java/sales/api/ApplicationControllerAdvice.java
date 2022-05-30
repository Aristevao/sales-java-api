package sales.api;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sales.domain.dto.response.ErrorEntity;
import sales.exception.BusinessLogicException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessLogicException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorEntity handleBusinessLogicException(BusinessLogicException exception) {
        final String message = exception.getMessage();
        return new ErrorEntity(message);
    }
}
