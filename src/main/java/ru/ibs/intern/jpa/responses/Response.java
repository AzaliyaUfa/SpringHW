package ru.ibs.intern.jpa.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Response {

    private Long elementNumber;
    private String statusMessage;

    public Response(Long id, String elementName, String action) {
        this.elementNumber = id;
        this.statusMessage = elementName + " with id = " + id + " was successfully " + action + ".";
    }

}
