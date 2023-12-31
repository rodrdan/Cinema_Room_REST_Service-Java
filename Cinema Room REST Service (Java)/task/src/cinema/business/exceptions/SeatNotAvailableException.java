package cinema.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SeatNotAvailableException extends RuntimeException{
    public SeatNotAvailableException () {
        super("{\"error\": \"The ticket has been already purchased!\"}");
    }
}
