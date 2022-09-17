package src.domain.model;

import java.util.Objects;

public class Rota {
    public Aeroporto aeroportoOrigem;
    public Aeroporto aeroportoDestino;
    public String rotaMinima;

    public Rota(Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino) {

        this.aeroportoOrigem = aeroportoOrigem;
        this.aeroportoDestino = aeroportoDestino;
    }

    public void encontraRotaMinima(Graph grafo, Node<Aeroporto> destino){
        StringBuilder rotaMinima = new StringBuilder();

        for(Node<Aeroporto> node : grafo.getNodes()){
            if(Objects.equals(node.getValue().codigo, destino.getValue().codigo)){
                System.out.print(node.getValue().codigo + " shortest path "); //TODO: apagar prints
                System.out.println();
                for(Node<Aeroporto> no: node.getShortestPath()){
                    rotaMinima.append(no.getValue().codigo).append(" - ");
                    System.out.print(no.getValue().codigo);
                }
            }

            System.out.println();
        }

        rotaMinima.append(destino.getValue().codigo);
        this.rotaMinima = rotaMinima.toString();
        System.out.println(rotaMinima.toString());
    }

}
