package ru.tinkoff.edu.java.bot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.tinkoff.edu.java.bot.DTO.ApiErrorResponse;
import ru.tinkoff.edu.java.bot.exceptions.ChatNotFoundException;
import ru.tinkoff.edu.java.bot.exceptions.LinkNotFoundException;

import java.util.Arrays;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ExceptionApiHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({ChatNotFoundException.class, LinkNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> chatNotFoundExceptions(RuntimeException exception) {
        ApiErrorResponse response = createError(exception, BAD_REQUEST.getReasonPhrase(), BAD_REQUEST.toString());
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    private ApiErrorResponse createError(Throwable exception, String description, String code) {
        return new ApiErrorResponse(
                description, code, exception.getClass().getName(),
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
    }
}
