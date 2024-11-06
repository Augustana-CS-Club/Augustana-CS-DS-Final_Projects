/**
 * This is a implemetation of a simple graph datastructure. 
 * @author Kaleb Gebrehiwot
 * @version 12.11.2023
 * 
 * COSC 260
 * Final-Project
 */
import java.util.ArrayList;

public class Graph{
    private ArrayList<Vertex> vertices;
   
    public Graph(){
        vertices = new ArrayList<>();
    }    

    public ArrayList<Vertex> getVertices(){
        return vertices;
    }

    public void AddEdge(Vertex v1, Vertex v2, int weight){
        // Add edge with destination of v2
        v1.getEdges().add(new Edge(v2, weight));
    }

    public boolean AddVertex(Vertex v){
        return vertices.add(v);
    }

    public void printGraph(){
        // print graph, for debugging purposes 
        for(Vertex v : vertices){
            System.out.print("vertex name: "+ v.getName() + ": ");
            for(Edge e : v.getEdges()){
                System.out.print("destVertex: " + e.getDestVertex().getName() + " weight: " + e.getWeight() + " | ");
            }
            System.out.print("\n");
        }
    }
    public void ResetRegion(){
        // This resets all Verticies to original state. Allows reruns of the Dijktra's algorithm
        for(Vertex v : vertices){
            v.setDistance(Integer.MAX_VALUE);
            v.setPredecessor(null);
        }
    }
}
