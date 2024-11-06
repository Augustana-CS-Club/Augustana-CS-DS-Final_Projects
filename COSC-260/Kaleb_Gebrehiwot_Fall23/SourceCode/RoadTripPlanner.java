/**
 * This is the driver class for the RoadTripPlanner program
 * @author Kaleb Gebrehiwot
 * @version 12.11.2023
 * 
 * COSC 260
 * Final-Project
 */
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class RoadTripPlanner{
    public static void main(String[] args)  throws IOException{
        // Initializing a Graph and a stack to store the shortest path
        Graph MidWestGraph = new Graph();
        GenericStack<Vertex> shortestPath = new GenericStack<Vertex>();
        // Read in the city files
        ReadInCities(MidWestGraph);


        // Prompt the user to enter the cities they want to visit
        Scanner input = new Scanner(System.in);
        System.out.printf("\t\t\t%s%n", "Welcome to your personal Road-Trip Planner");
        System.out.println("Enter cities you want to visit, comma separated and in order. Enter 'List', for a list of cities: ");
        // Proccess the input
        String inputString = input.nextLine();
        if(inputString.matches("List")){
            // List cities
            for(int i = 0; i < MidWestGraph.getVertices().size(); i++){
                if(i % 6 == 0){
                    System.out.print("\n\t" + (i+1) + "."+ MidWestGraph.getVertices().get(i).getName());
                    
                    continue;
                } 
                System.out.print("\t" + (i+1) + "." + MidWestGraph.getVertices().get(i).getName());
            }
            // Prompt the user again 
            System.out.println("\nEnter cities you want to visit, comma separated and in order: ");
            inputString = input.nextLine();
        }

        String[] citiesToVisit_String = inputString.split(",");
        ArrayList<Vertex> citiesToVisit = new ArrayList<Vertex>();

        // get the vertices of the user selected cities to visit, and Prompt for places to visit in said cities
        for(String s : citiesToVisit_String){
            Vertex v = Pathfinder.FindVertex(s, MidWestGraph);
            citiesToVisit.add(v);
            PromptPlacesToVisit(v);
        }

        // Handle pathfinding 
        System.out.println("\t----------------------------------------------------");
        System.out.println("\t\t\tROAD-TRIP SUMMARY: \n");
        Pathfinder.FindRoute(citiesToVisit, MidWestGraph, shortestPath);
        
        input.close();
    }
    

    public static void ReadInCities(Graph region)   throws IOException{
        // This function reads in the city files which are in a sub-directory called cities

        String[] cityFiles = new File("Cities").list();
        
        // Create the a vertex for each city
        for(String s : cityFiles){
            String cityName = s.substring(0,s.length()-4);
            region.AddVertex(new Vertex(cityName));
        }
        // Read in city's information 
        for (Vertex vertex : region.getVertices()) {
            String line = "" ;
            String dirDelimiter = (System.getProperty("os.name").startsWith("Windows"))? "\\" : "/";
            BufferedReader br = new BufferedReader(new FileReader("Cities" + dirDelimiter + vertex.getName() + ".txt")); // might need to change the backslashes to forwardslashes if running on mac
            // Create the Edges connecting the cities    
            while(!(line = br.readLine()).matches("break,")){
                String connectingCity = line.split(",")[0];
                int dist = Integer.parseInt(line.split(",")[1]);
                // Find connecting city vertex
                Vertex ccVertex = Pathfinder.FindVertex(connectingCity, region);
                
                region.AddEdge(vertex, ccVertex, dist);
            }
            // Read in accomodations for this city 
            while(!(line = br.readLine()).matches("break,")){
                if(line.matches("Accomodations,")){continue;}
                String[] details = line.split(",");
                vertex.getAccommodations().add(new Accommodation(details[0], Double.parseDouble(details[1]), Double.parseDouble(details[2]), details[3], Float.parseFloat(details[4]), Integer.parseInt(details[5])));
            } 
            // Read in the TouristAttractions in this city
            while(!(line = br.readLine()).matches("break,")){
                if(line.matches("TouristAttractions,")){continue;}
                String[] details = line.split(","); 
                vertex.getTouristAttractions().add(new TouristAttraction(details[0], Double.parseDouble(details[1]), Double.parseDouble(details[2]), Integer.parseInt(details[3]), details[4]));
            } 
            // Read in places to eat in this city
            while(!(line = br.readLine()).matches("break,")){
                if(line.matches("FoodPlaces,")){continue;}
                String[] details = line.split(",");
                vertex.getFoodPlaces().add(new FoodPlace(details[0], Double.parseDouble(details[1]), Double.parseDouble(details[2]), details[3], details[4], Boolean.parseBoolean(details[5])));
            }   
            br.close();   
        }


    }

    public static void PromptPlacesToVisit(Vertex v){
        //This function Prompts the user for the places they want to visit in the cities they picked
        if(v.getAccommodations().size() < 1 && v.getTouristAttractions().size() < 1 && v.getFoodPlaces().size() < 1){
            return;
        }
        System.out.printf("------------------------------%s----------------------------------%n%n", v.getName());
        System.out.printf("Enter the indices of the places you want to visit in %s. Comma separated (eg: 1,2,3):%n ", v.getName());
        System.out.println(v.ptvList());
        Scanner input = new Scanner(System.in);
        String inputString = input.nextLine();
        v.ChoosePlacesToVisit(inputString.split(",")); 
        
    }

    public static int PrintPath(GenericStack<Vertex> path){
        // This function goes through a Stack printing out the city names in order and their distance between each other
        // Also return total milage
        int milage = 0;
        Vertex prevVertex = null;
        while(!path.isEmpty()){
            
            Vertex v = path.pop();
            
            int travel = 0;
            if(prevVertex != null){
                travel = v.getCurrentDist() - prevVertex.getCurrentDist();
                milage += travel;
            }
            if(travel > 0) System.out.printf("  |  %d miles\n  V%n", travel);
            
            System.out.println(v.toString());
            prevVertex = v;
        }
        return milage;
    }

    public static void PrintSummary(int milage, Graph region){
        // Prints out the summary of trip
        float estimatedTT = (float)milage/80;

        Double estimatedCost  = 0.0;
        for(Vertex v : region.getVertices()){
            if(v.isPrintedAlready()){
                estimatedCost += v.getPTVCost();
            }
        }
        estimatedCost += milage * 0.655f; // 0.655 is the average cost of driving per mile in the US
        System.out.printf(" Total Milage: %d%n Estimated Travel-Time: %.2fhrs%n Estimated cost: %.2f$%n",
                                milage, estimatedTT, estimatedCost);
        
    }

}