package ru.ibs.intern.jpa.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@NoArgsConstructor
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no engine with such id")
public class NoSuchEngineException extends RuntimeException {
    public NoSuchEngineException(String message) {
        System.out.println(message);
    }
}
