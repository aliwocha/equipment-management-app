package pl.javastart.equipy.components.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Użytkownik z tym numerem pesel już istnieje")
public class DuplicatePeselException extends RuntimeException {
}
