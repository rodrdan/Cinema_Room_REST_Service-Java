package cinema.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SeatNotFoundException extends RuntimeException{
    public SeatNotFoundException () {
        super("{\"error\": \"The number of a row or a column is out of bounds!\"}");
    }
}
