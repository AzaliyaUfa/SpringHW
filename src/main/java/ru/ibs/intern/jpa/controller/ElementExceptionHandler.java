package ru.ibs.intern.jpa.service.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.ibs.intern.jpa.exceptions.NoElementException;
import ru.ibs.intern.jpa.exceptions.NoIdException;

@ControllerAdvice
public class ElementExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoElementException.class)
    protected ResponseEntity<MessageException> handleNoElementException() {
        return new ResponseEntity<>(new MessageException("There is no element with such id", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoIdException.class)
    protected ResponseEntity<MessageException> handleNoIdException() {
        return new ResponseEntity<>(new MessageException("Empty Id!", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class MessageException {
        private String message;
        private HttpStatus status;
    }

}
