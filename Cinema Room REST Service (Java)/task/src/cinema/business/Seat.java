package cinema.business;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class Seat {
    @JsonProperty("row")
    private int rowNumber;
    @JsonProperty("column")
    private int columnNumber;
    @JsonIgnore
    private boolean available;
    @JsonProperty("price")
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonIgnore
    private UUID token;

    @JsonCreator
    public Seat(@JsonProperty("row") int rowNumber, @JsonProperty("column") int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.available = true;
        if (rowNumber <= 4) {
            this.price = 10;
        } else {
            this.price = 8;
        }
        this.token = UUID.randomUUID();
    }

    public int getRowNumber() {
        return rowNumber;
    }
    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public UUID getToken() {
        return token;
    }
    public void setToken(UUID token) {
        this.token = token;
    }
    public boolean isAvailable() {
        return this.available;
    }
    public void setUnavailable(boolean unavailable) {
        this.available = false;
    }
    public void markAsSold() {
        this.available = false;
    }
    public void markAsAvailable() {
        this.available = true;
    }



}
