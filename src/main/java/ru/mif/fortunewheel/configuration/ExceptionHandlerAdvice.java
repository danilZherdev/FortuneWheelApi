package ru.mif.fortunewheel.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.mif.fortunewheel.dto.ErrorResponse;
import ru.mif.fortunewheel.security.AuthenticationException;
import ru.mif.fortunewheel.security.ForbiddenException;
import ru.mif.fortunewheel.service.ServiceException;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> generalHandler(ServiceException exception) {
        ErrorResponse error = new ErrorResponse(
                exception.getMessage(),"", ZonedDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ErrorResponse> generalAuthenticationCredentialsNotFoundHandler(AuthenticationCredentialsNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(
                exception.getMessage(),"", ZonedDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> generalAuthenticationHandler(AuthenticationException exception) {
        ErrorResponse error = new ErrorResponse(
                exception.getMessage(),"", ZonedDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> generalForbiddenHandler(ForbiddenException exception) {
        ErrorResponse error = new ErrorResponse(
                exception.getMessage(),"", ZonedDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
