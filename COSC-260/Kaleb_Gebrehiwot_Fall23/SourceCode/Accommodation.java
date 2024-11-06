/**
 * This is class extends class PlaceToVisit, and represent a place to stay in a city
 * @author Kaleb Gebrehiwot
 * @version 12.11.2023
 * 
 * COSC 260
 * Final-Project
 */

public class Accommodation extends PlaceToVisit{
    
    private String type;
    private float rating;
    private int maxCapacity;
    
    public Accommodation(String name, Double timeCom, Double avgCost, String type, float rating, int maxCapacity) {
        super(name, timeCom, avgCost);
        this.type = type;
        this.rating = rating;
        this.maxCapacity = maxCapacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String toString(){
        return String.format("%s    Type: %s, Rating: %.2f/5 stars, Max. per room: %d people%n", super.toString(), type, rating, maxCapacity);
    }
}
