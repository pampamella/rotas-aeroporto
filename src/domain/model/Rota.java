package src.domain.model;

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

    public void setAeroportoOrigem(Aeroporto aeroportoOrigem) {
        this.aeroportoOrigem = aeroportoOrigem;
    }

    public Aeroporto getAeroportoDestino() {
        return aeroportoDestino;
    }

    public void setAeroportoDestino(Aeroporto aeroportoDestino) {
        this.aeroportoDestino = aeroportoDestino;
    }

    public String getRotaMinima() {
        return rotaMinima;
    }

    public void setRotaMinima(String rotaMinima) {
        this.rotaMinima = rotaMinima;
    }

    public void encontraRotaMinima(Graph grafo, Node<Aeroporto> destino){
        StringBuilder rotaMinima = new StringBuilder();

        for(Node<Aeroporto> node : grafo.getNodes()){
            if(Objects.equals(node.getValue().getCodigo(), destino.getValue().getCodigo())){
                System.out.print(node.getValue().getCodigo() + " shortest path "); //TODO: apagar prints
                System.out.println();
                for(Node<Aeroporto> no: node.getShortestPath()){
                    rotaMinima.append(no.getValue().getCodigo()).append(" - ");
                    System.out.print(no.getValue().getCodigo());
                }
            }

            System.out.println();
        }

        rotaMinima.append(destino.getValue().getCodigo());
        this.rotaMinima = rotaMinima.toString();
        System.out.println(rotaMinima.toString());
    }

}
