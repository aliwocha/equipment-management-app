package pl.javastart.equipy.components.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAssignmentException extends RuntimeException {

    public InvalidAssignmentException(String message) {
        super(message);
    }
}
