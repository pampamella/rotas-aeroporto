package src.domain.model;

import java.util.*;

public class Node<T> {

    private final T value;

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