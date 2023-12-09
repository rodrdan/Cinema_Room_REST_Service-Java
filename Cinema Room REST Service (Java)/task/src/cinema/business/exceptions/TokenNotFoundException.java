package cinema.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TokenNotFoundException extends RuntimeException{
    public TokenNotFoundException () {
        super("{\"error\": \"Wrong token!\"}");
    }
}
