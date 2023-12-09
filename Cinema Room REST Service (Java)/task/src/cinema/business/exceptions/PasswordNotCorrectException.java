package cinema.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class PasswordNotCorrectException extends RuntimeException{
    public PasswordNotCorrectException() {
        super("{\"error\": \"The password is wrong!\"}");
    }
}
