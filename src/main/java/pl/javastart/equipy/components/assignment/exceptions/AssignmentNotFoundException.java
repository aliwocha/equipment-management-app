package pl.javastart.equipy.components.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Brak przypisania o podanym id")
public class AssignmentNotFoundException extends RuntimeException {
}
