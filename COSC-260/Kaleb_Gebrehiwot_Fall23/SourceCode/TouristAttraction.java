/**
 * This class is an extension of the PlaceToVisit class and is a representation of a TouristAttraction in a certain city
 * @author Kaleb Gebrehiwot
 * @version 12.11.2023
 * 
 * COSC 260
 * Final-Project
 */
public class TouristAttraction extends PlaceToVisit{

    private int traffic;
    private String description;
    public TouristAttraction(String name, Double timeCom, Double avgCost, int traffic, String description) {
        super(name, timeCom, avgCost);
        this.traffic = traffic;
        this.description = description;
    }

    public int getTraffic() {
        return traffic;
    }
    public void setTraffic(int traffic) {
        this.traffic = traffic;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return String.format("%s     Usual traffic: %d people%n     Description: %s%n", super.toString(), traffic, description);
    }
    
}