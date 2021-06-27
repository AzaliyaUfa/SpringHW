package ru.ibs.intern.jpa.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.ibs.intern.jpa.exceptions.LinkedItemException;
import ru.ibs.intern.jpa.exceptions.NoElementException;
import ru.ibs.intern.jpa.exceptions.NoIdException;
import ru.ibs.intern.jpa.exceptions.NoSuchEngineException;

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

    @ExceptionHandler(NoSuchEngineException.class)
    protected ResponseEntity<MessageException> handleNoSuchEngineException() {
        return new ResponseEntity<>(new MessageException("Gear with wrong or empty engine Id!", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LinkedItemException.class)
    protected ResponseEntity<MessageException> handleLinkedItemException() {
        return new ResponseEntity<>(new MessageException("There is an item, connected with this element.", HttpStatus.NOT_ACCEPTABLE), HttpStatus.NOT_ACCEPTABLE);
    }

    @Data
    @AllArgsConstructor
    private static class MessageException {
        private String message;
        private HttpStatus status;
    }

}
