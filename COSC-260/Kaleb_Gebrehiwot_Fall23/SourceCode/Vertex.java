/**
 * This class serves as a Vertex(node) in a graph datastructure. It also includes additional instace variables 
 * to represent a city
 * @author Kaleb Gebrehiwot
 * @version 12.11.2023
 * 
 * COSC 260
 * Final-Project
 */
import java.util.ArrayList;

public class Vertex{
    private String name;
    private ArrayList<Edge> edgeList;
    private int currentDist = Integer.MAX_VALUE;
    private Vertex predecessor = null; // the preceeding vertex on the shortest path. Creates pseudo linked list.
    private boolean printedAlready = false;
    
    private ArrayList<Accommodation> accommodations = new ArrayList<Accommodation>();
    private ArrayList<TouristAttraction> touristAttractions = new ArrayList<TouristAttraction>();
    private ArrayList<FoodPlace> foodPlaces = new ArrayList<FoodPlace>();

    private ArrayList<Integer> ptvIndices = new ArrayList<Integer>(); // selected places to visit indices

    // -------------------- Getters and setter ---------------------------
    public ArrayList<Accommodation> getAccommodations() {
        return accommodations;
    }
    public ArrayList<TouristAttraction> getTouristAttractions() {
        return touristAttractions;
    }
    public ArrayList<FoodPlace> getFoodPlaces() {
        return foodPlaces;
    }
    public Vertex(String name){
        this.name = name;
        edgeList = new ArrayList<>();
    }

    public Vertex getPredecessor(){
        return predecessor;
    }
    public int getCurrentDist(){
        return currentDist;
    }
 
    public void setPredecessor(Vertex newPred){
        this.predecessor = newPred;
    }
    public void setDistance(int newDist){
        this.currentDist = newDist;
    }
    public String getName(){
        return name;
    }

    public ArrayList<Edge> getEdges(){
        return edgeList;
    }
    
    public boolean isPrintedAlready(){
        return printedAlready;
    }
    public void Printed(){
        printedAlready = true;
    }
    //---------------------------------------------------

    

    public String ptvList(){
        // returns a list of all available places to visit
        String list = "\n\t\tAccommodations\n";
        int indexer = 1;
        for(Accommodation acc : accommodations){
            list += "\n" + indexer + ") " + acc.toString();
            indexer++;
        }
        list += "\n\t\tTourist-Attractions\n";
        for(TouristAttraction ta : touristAttractions){
            list += "\n" + indexer + ") " + ta.toString();
            indexer++;
        }
        list += "\n\t\tFood Places\n";
        for(FoodPlace fp : foodPlaces){
            list += "\n" + indexer + ") " + fp.toString();
            indexer++;
        }
        return list;
    }
    public void ChoosePlacesToVisit(String[] choice){
        // Pick places to visit 
        for(String c : choice){
            int i  = Integer.parseInt(c) - 1;
            ptvIndices.add(i);
        }
    }
    public Double getPTVCost(){
        // This function returns the total cost of picked places to visit
        Double totalCost = 0.0;
        for(int i = 0; i < ptvIndices.size(); i++){
            if(ptvIndices.get(i) < accommodations.size()){
                totalCost += accommodations.get(ptvIndices.get(i)).getAverageCost();
            }else if(ptvIndices.get(i) < accommodations.size() + touristAttractions.size()){
                totalCost += touristAttractions.get(ptvIndices.get(i) - accommodations.size()).getAverageCost();
            }else if(ptvIndices.get(i) < accommodations.size() + touristAttractions.size() + foodPlaces.size()){
                totalCost += foodPlaces.get(ptvIndices.get(i) - accommodations.size() - touristAttractions.size()).getAverageCost();
            }
        }
        return totalCost;
    }

    public String toString(){
        // Return the city name(with selected places to visit, if any)
        String visitDetails = name + "\n";
        if(ptvIndices.size() < 1  || this.isPrintedAlready()){
            return visitDetails;
        }
        visitDetails += "- Places to visit: ";
        for(int i = 0; i < ptvIndices.size(); i++){
            if(ptvIndices.get(i) < accommodations.size()){
                visitDetails += "\n\t" + accommodations.get(ptvIndices.get(i)).getName();
            }else if(ptvIndices.get(i) < accommodations.size() + touristAttractions.size()){
                visitDetails += "\n\t" + touristAttractions.get(ptvIndices.get(i) - accommodations.size()).getName();
            }else if(ptvIndices.get(i) < accommodations.size() + touristAttractions.size() + foodPlaces.size()){
                visitDetails += "\n\t" + foodPlaces.get(ptvIndices.get(i) - accommodations.size() - touristAttractions.size()).getName();
            }
        }
        this.Printed();
        return String.format("%s%n", visitDetails);
    }
    
}