package Entities;

import java.util.HashMap;

public class Graph {

    private int root;
    private HashMap<Integer, Vertex> vertices;
    private HashMap<Integer, HashMap<String, Integer>> edges;

    public Graph(int root, HashMap<Integer,Vertex> vertices, HashMap<Integer, HashMap<String,Integer>> edges){
        this.root = root;
        this.vertices = vertices;
        this.edges = edges;
    }

    public HashMap<Integer, HashMap<String,Integer>> getEdges() {
        return edges;
    }

    public HashMap<Integer, Vertex> getVertices() {
        return vertices;
    }

    public int getRoot() {
        return root;
    }

    public static void main(String[] args) {

    }
}