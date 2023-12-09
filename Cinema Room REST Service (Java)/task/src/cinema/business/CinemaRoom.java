package cinema.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonPropertyOrder({
        "totalRows",
        "totalColumns",
        "availableSeats"
})
@Component
public class CinemaRoom {
    @JsonProperty("rows")
    private final int totalRows;
    @JsonProperty("columns")
    private final int totalColumns;
    @JsonProperty("seats")
    private final List<Seat> allSeats;
    @JsonIgnore
    private final CinemaRoomStatistics statistics;

    public CinemaRoom() {
        this.totalRows = 9;
        this.totalColumns = 9;
        this.allSeats = new ArrayList<>();
        prepareSeats();
        this.statistics = new CinemaRoomStatistics(9, 9);
    }
    public List<Seat> getAllSeats() {
        return this.allSeats;
    }
    public void prepareSeats() {
        for (int i = 0; i < this.totalRows; i++) {
            for (int j = 0; j < this.totalColumns; j++) {
                this.allSeats.add(new Seat(i + 1 , j + 1));
            }
        }
    }
    public Seat getSeat(int row, int column) {
        for (Seat seatToFind : this.getAllSeats()) {
            if (seatToFind.getRowNumber() == row && seatToFind.getColumnNumber() == column) {
                return seatToFind;
            }
        }
        return null;
    }
    public Seat getSeat(UUID token) {
        for (Seat seatToFind : this.getAllSeats()) {
            if (seatToFind.getToken().equals(token)) {
                return seatToFind;
            }
        }
        return null;
    }
    public CinemaRoomStatistics getStatistics() {
        return this.statistics;
    }
    public void markAsUnavailable(Seat seatToRemove) {
        seatToRemove.markAsSold();
    }
    public void updateStatisticsWithPurchase(Seat seat) {
        this.statistics.ticketSold(seat);
    }
    public void updateStatisticsWithReturn(Seat seat) {
        this.statistics.ticketReturned(seat);
    }

}
