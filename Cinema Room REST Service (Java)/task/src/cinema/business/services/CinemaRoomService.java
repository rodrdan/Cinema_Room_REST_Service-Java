package cinema.business.services;

import cinema.business.CinemaRoom;
import cinema.business.Seat;
import cinema.business.exceptions.PasswordNotCorrectException;
import cinema.business.exceptions.SeatNotAvailableException;
import cinema.business.exceptions.SeatNotFoundException;
import cinema.business.exceptions.TokenNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CinemaRoomService {
    @Autowired
    CinemaRoom cinemaRoom;

    public ResponseEntity<?> processPurchase(Seat wantedSeat, CinemaRoom cinemaRoom)  throws JsonProcessingException {
        Seat seatToFind = cinemaRoom.getSeat(wantedSeat.getRowNumber(), wantedSeat.getColumnNumber());
        if (seatToFind == null) {
            throw new SeatNotFoundException();
        } else if (!seatToFind.isAvailable()) {
            throw new SeatNotAvailableException();
        } else {
            cinemaRoom.markAsUnavailable(seatToFind);
            cinemaRoom.updateStatisticsWithPurchase(seatToFind);
            Map<Object, Object> data = new LinkedHashMap<>();
            data.put("token", seatToFind.getToken().toString());
            data.put("ticket", seatToFind);
            ObjectMapper objectMapper = new ObjectMapper();
            String seatInfo = objectMapper.writeValueAsString(data);
            System.out.println(seatInfo);
            return ResponseEntity.ok().body(seatInfo);
        }
    }
    public ResponseEntity<?> processReturn(UUID token) throws JsonProcessingException {
        Seat seat = cinemaRoom.getSeat(token);
        if (seat == null || seat.isAvailable()) {
            throw new TokenNotFoundException();
        } else {
            seat.markAsAvailable();
            cinemaRoom.updateStatisticsWithReturn(seat);
            Map<String, Seat> data = new LinkedHashMap<>();
            data.put("ticket", seat);
            ObjectMapper objectMapper = new ObjectMapper();
            String seatInfo = objectMapper.writeValueAsString(data);
            System.out.println(seatInfo);
            return ResponseEntity.ok().body(seatInfo);
        }
    }
    public ResponseEntity<?> processStatistics(String password) throws JsonProcessingException {
        if (password.equals("super_secret")) {
            ObjectMapper objectMapper = new ObjectMapper();
            String statistics = objectMapper.writeValueAsString(this.cinemaRoom.getStatistics());
            System.out.println(statistics);
            return ResponseEntity.ok().body(statistics);
        } else {
            throw new PasswordNotCorrectException();
        }
    }

}
