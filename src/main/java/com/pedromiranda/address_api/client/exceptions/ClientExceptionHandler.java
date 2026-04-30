package com.pedromiranda.address_api.client.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler(CepException.class)
    public ResponseEntity<StandardError> cardNotFoundException(CepException ex, HttpServletRequest request) {
        String err = "INVALID CEP";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(
                LocalDateTime.now(),
                status.value(),
                ex.getMessage(),
                err,
                request.getRequestURI()
        );

        return ResponseEntity
                .status(status)
                .body(error);
    }
}
