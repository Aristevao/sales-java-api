package sales.api;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sales.domain.dto.response.ErrorEntity;
import sales.common.exception.BusinessLogicException;
import sales.common.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessLogicException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorEntity handleBusinessLogicException(BusinessLogicException exception) {
        final String message = exception.getMessage();
        return new ErrorEntity(message);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorEntity handleNotFoundException(NotFoundException exception) {
        final String message = exception.getMessage();
        return new ErrorEntity(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> messages = exception.getBindingResult()
                .getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ErrorEntity(messages);
    }
}
