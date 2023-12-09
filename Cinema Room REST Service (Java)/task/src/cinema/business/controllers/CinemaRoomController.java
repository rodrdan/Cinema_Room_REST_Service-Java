package cinema.business.controllers;

import cinema.business.CinemaRoom;
import cinema.business.Seat;
import cinema.business.exceptions.PasswordIsMissingException;
import cinema.business.services.CinemaRoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CinemaRoomController {
    @Autowired
    private CinemaRoom cinemaRoom;
    @Autowired
    private CinemaRoomService cinemaRoomService;

    @GetMapping("/seats")
    public String getSeats() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cinemaRoom);
    }
    @PostMapping("/purchase")
    public ResponseEntity<?> buyTicket(@RequestBody Seat seat) throws JsonProcessingException {
        return this.cinemaRoomService.processPurchase(seat, cinemaRoom);
    }
    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Map<String, UUID> tokenMap) throws JsonProcessingException {
        return this.cinemaRoomService.processReturn(tokenMap.get("token"));
    }
    @GetMapping("/stats")
    public ResponseEntity<?> getStatistics(@RequestParam(required = false) Optional<String> password) throws JsonProcessingException{
        if (password.isPresent()) {
            return this.cinemaRoomService.processStatistics(password.get());
        } else {
            throw new PasswordIsMissingException();
        }
    }


}
