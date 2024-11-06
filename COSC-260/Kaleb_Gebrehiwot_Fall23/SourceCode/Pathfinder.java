/**
 * This class implements the Dijktra's algorithm to find the shortest route to a vertex
 * @author Kaleb Gebrehiwot
 * @version 12.11.2023
 * 
 * COSC 260
 * Final-Project
 */
import java.util.ArrayList;
public class Pathfinder {

    public static void FindRoute(ArrayList<Vertex> citiesToVisit, Graph region, GenericStack<Vertex> path){

        int totalMilage = 0;
        // call the the ShortestPath() function for each city to the next 
        for(int i = 0; i < citiesToVisit.size() - 1; i++ ){
            ShortestPath(citiesToVisit.get(i), citiesToVisit.get(i+1), region, path);
            // Print out path
            totalMilage += RoadTripPlanner.PrintPath(path);
        }
        // Print out summary when done
        RoadTripPlanner.PrintSummary(totalMilage,region); 
    }


    public static Vertex FindVertex(String cityName, Graph region){
        // returns the Vertext with the passed in name, in a certain region
        for(Vertex v : region.getVertices()){
            if(v.getName().matches(cityName)){
                return v;
            }
        }
        return new Vertex("Invalid City");
    }
    //  --------------------------- Dijkstra's Algorithm -------------------------------------

        public static void ShortestPath(Vertex start, Vertex dest, Graph region, GenericStack<Vertex> path){
            
            // Find the shortest path using Dijktra's algo.
            region.ResetRegion();
            //Initialize unvisited vetices
            ArrayList<Vertex> unvisitedVertices = new ArrayList<>(region.getVertices());
            

            start.setDistance(0); // set start node dist = 0
            unvisitedVertices.remove(start);

            Vertex currentVector = start;
            // Go through all the vertices performing the two main steps in Dijktra's algo
            while(unvisitedVertices.size() > 0){
                UpdateAdjecentVertices(currentVector);
                currentVector = NextVertex(unvisitedVertices);
                unvisitedVertices.remove(currentVector);
            }
            // Backtrack through the shortest path to get it in order
            Vertex currVert = dest;
            while( currVert != null && currVert != start){
                path.push(currVert);
                currVert = currVert.getPredecessor();
            }
            path.push(start);
            
        }
        public static void UpdateAdjecentVertices(Vertex currentV){
            // update the shortest distances from the origin of all vertices that connect to the current vertex
            for(Edge e : currentV.getEdges()){
                if(e.getDestVertex().getCurrentDist() > currentV.getCurrentDist() + e.getWeight()){
                    e.getDestVertex().setDistance(currentV.getCurrentDist() + e.getWeight());
                    e.getDestVertex().setPredecessor(currentV);
                }
            }
        }
        public static Vertex NextVertex(ArrayList<Vertex> unvisitedVertices){
            // Pick the next Vertex (the closest to the origin, and yet visited)
            Vertex next = null;
            for(Vertex v : unvisitedVertices){
                if(next == null || v.getCurrentDist() < next.getCurrentDist()){
                    next = v;
                }
            } 
            return next;
        }
    // ----------------------------------------------------------------------------------------
}
