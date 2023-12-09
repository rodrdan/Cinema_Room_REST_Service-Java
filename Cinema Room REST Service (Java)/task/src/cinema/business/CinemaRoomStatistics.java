package cinema.business;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class CinemaRoomStatistics {
    @JsonProperty("income")
    private int income;
    @JsonProperty("available")
    private int availableTickets;
    @JsonProperty("purchased")
    private int purchasedTickets;

    public CinemaRoomStatistics(int rows, int columns) {
        this.income = 0;
        this.availableTickets = rows * columns;
        this.purchasedTickets = 0;
    }
    public void ticketSold(Seat seat) {
        this.income += seat.getPrice();
        this.availableTickets--;
        this.purchasedTickets++;
    }
    public void ticketReturned(Seat seat) {
        this.income -= seat.getPrice();
        this.availableTickets++;
        this.purchasedTickets--;
    }

}
