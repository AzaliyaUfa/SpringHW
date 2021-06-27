package ru.ibs.intern.jpa.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Empty Id")
public class NoIdException extends RuntimeException {

    public NoIdException() {
        System.out.println("Empty Id");
    }
}
