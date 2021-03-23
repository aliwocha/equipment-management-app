package pl.javastart.equipy.components.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Użytkownik o podanym id nie istnieje")
public class UserNotFoundException extends RuntimeException{
}
