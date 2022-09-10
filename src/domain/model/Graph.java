package src.domain.model;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<Node<Aeroporto>> nodes = new HashSet<>();

    public void addNode(Node<Aeroporto> nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node<Aeroporto>> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node<Aeroporto>> nodes) {
        this.nodes = nodes;
    }
}