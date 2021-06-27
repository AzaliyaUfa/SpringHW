package ru.ibs.intern.jpa.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "There is an item, connected with this element")
@NoArgsConstructor
public class LinkedItemException extends RuntimeException {
    public LinkedItemException(String message) {
        System.out.println(message);
    }
}
