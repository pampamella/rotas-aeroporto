package domain.model;

import java.util.Objects;

public class Rota {
    private Aeroporto aeroportoOrigem;
    private Aeroporto aeroportoDestino;
    private String rotaMinima;

    public Rota(Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino) {

        this.aeroportoOrigem = aeroportoOrigem;
        this.aeroportoDestino = aeroportoDestino;
    }

    public Aeroporto getAeroportoOrigem() {
        return aeroportoOrigem;
    }

    public Aeroporto getAeroportoDestino() {
        return aeroportoDestino;
    }

    public String getRotaMinima() {
        return rotaMinima;
    }

    public void encontraRotaMinima(Graph grafo, Node<Aeroporto> destino){
        StringBuilder rotaMinima = new StringBuilder();

        for(Node<Aeroporto> node : grafo.getNodes()){
            if(Objects.equals(node.getValue().getCodigo(), destino.getValue().getCodigo())){
                for(Node<Aeroporto> no: node.getShortestPath()){
                    rotaMinima.append(no.getValue().getCodigo()).append(" (").append(no.getValue().getNomeCompleto()).append(") ").append(" - ");
                }
            }
        }

        rotaMinima.append(destino.getValue().getCodigo()).append(" (").append(destino.getValue().getNomeCompleto()).append(") ");
        this.rotaMinima = rotaMinima.toString();
        System.out.println("Rota mínima entre aeroportos:");
        System.out.println(rotaMinima);
    }

}
