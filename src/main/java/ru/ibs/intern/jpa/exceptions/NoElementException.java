package ru.ibs.intern.jpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no element with such id")
public class NoElementException extends RuntimeException {
    public NoElementException(String message) {
        System.out.println(message);
    }
}
