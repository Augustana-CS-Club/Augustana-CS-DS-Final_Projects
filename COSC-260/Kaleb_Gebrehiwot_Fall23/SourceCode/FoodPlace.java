/**
 * This class extends the PlaceToVisit class and represent a place to eat in a city.
 * @author Kaleb Gebrehiwot
 * @version 12.11.2023
 * 
 * COSC 260
 * Final-Project
 */
public class FoodPlace extends PlaceToVisit{
    
    private String type;
    private String options;
    private boolean reservation;
    public FoodPlace(String name, Double timeCom, Double avgCost, String type, String options, boolean reservation) {
        super(name, timeCom, avgCost);
        this.type = type;
        this.options = options;
        this.reservation = reservation;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getOptions() {
        return options;
    }
    public void setOptions(String options) {
        this.options = options;
    }
    public boolean isReservationRequired() {
        return reservation;
    }
    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public String toString(){
        return String.format("%s     Type: %s, Options: %s, Requires Reservation: %b%n", super.toString(),type, options, Boolean.parseBoolean(options));
    }
    
}