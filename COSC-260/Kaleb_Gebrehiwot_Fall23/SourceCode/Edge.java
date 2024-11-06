/**
 * This class is for representing a link between vetrices and the weight between.
 * @author Kaleb Gebrehiwot
 * @version 12.11.2023
 * 
 * COSC 260
 * Final-Project
 */
public class Edge{
    private int weight;
    private Vertex destVertex;

    public Edge(Vertex dest, int w){
        this.destVertex = dest;
        this.weight = w;
    }

    public int getWeight(){
        // will represent the distaces between the cities
        return weight;
    }

    public Vertex getDestVertex(){
        // returns connecting city
        return destVertex;
    }
}