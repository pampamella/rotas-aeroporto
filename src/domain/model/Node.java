package src.domain.model;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;

public class Node<T> {

    private T value;

    private LinkedList<Node<T>> shortestPath = new LinkedList<>();

    private Double distance = Double.POSITIVE_INFINITY;

    private Map<Node<T>, Double> adjacentNodes = new HashMap<>();

    public Node(T value) {
        this.value = value;
    }

    public void addDestination(Node<T> destination, double distance) {
        if(distance>0.0) {
            adjacentNodes.put(destination, distance);
        }
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Map<Node<T>, Double> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node<T>, Double> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<Node<T>> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<Node<T>> shortestPath) {
        this.shortestPath = shortestPath;
    }

}