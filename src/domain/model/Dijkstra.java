package src.domain.model;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class Dijkstra {

    public static Graph calculateShortestPathFromSource(Graph graph, Node<Aeroporto> source) {

        source.setDistance(0.0);

        Set<Node<Aeroporto>> settledNodes = new HashSet<>();
        Set<Node<Aeroporto>> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node<Aeroporto> currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry<Node<Aeroporto>, Double> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node<Aeroporto> adjacentNode = adjacencyPair.getKey();
                Double edgeWeigh = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static void CalculateMinimumDistance(Node<Aeroporto> evaluationNode, Double edgeWeigh, Node<Aeroporto> sourceNode) {
        Double sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node<Aeroporto>> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private static Node<Aeroporto> getLowestDistanceNode(Set<Node<Aeroporto>> unsettledNodes) {
        Node<Aeroporto> lowestDistanceNode = null;
        double lowestDistance = Double.POSITIVE_INFINITY;
        for (Node<Aeroporto> node : unsettledNodes) {
            double nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
}