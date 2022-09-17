package src.domain.model;

import src.domain.helpers.DistanciaMapa;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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

    public List<Node<Aeroporto>> conectaNodes(List<Node<Aeroporto>> nodeList) {
        double distance=0.0;
        for(Node<Aeroporto> node: nodeList){
            for (Node<Aeroporto> nodeIncident:nodeList){
                distance = DistanciaMapa.calculaDistancia(node.getValue(), nodeIncident.getValue());
                node.addDestination(nodeIncident, distance);
            }
        }
        return nodeList;
    }
    public List<Node<Aeroporto>> desconectaOrigemDestino(List<Node<Aeroporto>> nodeList, Node<Aeroporto> origem, Node<Aeroporto> destino) {
        for(Node<Aeroporto> node: nodeList){
            System.out.println(node.getValue().codigo);
             //TODO: tirar print
            if(Objects.equals(node.getValue().codigo, origem.getValue().codigo) || Objects.equals(node.getValue().codigo, destino.getValue().codigo)){
                System.out.println("Removendo");
                node.getAdjacentNodes().entrySet().removeIf(i -> Objects.equals(i.getKey().getValue().codigo, origem.getValue().codigo));
                node.getAdjacentNodes().entrySet().removeIf(i -> Objects.equals(i.getKey().getValue().codigo, destino.getValue().codigo));
            }
        }
        return nodeList;
    }
    public List<Node<Aeroporto>> reconectaOrigemDestino(List<Node<Aeroporto>> nodeList, Node<Aeroporto> origem, Node<Aeroporto> destino) {
        double distance=0.0;
        for(Node<Aeroporto> node: nodeList){
            System.out.println(node.getValue().codigo);
            //TODO: tirar print
            if(Objects.equals(node.getValue().codigo, origem.getValue().codigo) || Objects.equals(node.getValue().codigo, destino.getValue().codigo)){
                System.out.println("reconectando");
                distance = DistanciaMapa.calculaDistancia(node.getValue(), origem.getValue());
                node.addDestination(origem, distance);
                distance = DistanciaMapa.calculaDistancia(node.getValue(), destino.getValue());
                node.addDestination(destino, distance);
            }
        }
        return nodeList;
    }

}