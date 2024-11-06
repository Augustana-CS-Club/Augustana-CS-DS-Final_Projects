/**
 * This abstract class serves as a super class to all the types of places you can visit in a city
 * @author Kaleb Gebrehiwot
 * @version 12.11.2023
 * 
 * COSC 260
 * Final-Project
 */
public abstract class PlaceToVisit{
    private String name;
    private Double timeCommitment;
    private Double averageCost;

    public PlaceToVisit(String name, Double timeCom, Double avgCost){
        this.name = name;
        this.timeCommitment = timeCom;
        this.averageCost = avgCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTimeCommitment() {
        return timeCommitment;
    }

    public void setTimeCommitment(Double timeCommitment) {
        this.timeCommitment = timeCommitment;
    }

    public Double getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(Double averageCost) {
        this.averageCost = averageCost;
    }

    public String toString(){
        return String.format("Name: %s, Time commitment: %.2f, Average cost: %.2f%n", name, timeCommitment, averageCost);
    }
    
}