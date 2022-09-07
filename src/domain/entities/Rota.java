package src.domain.entities;

public class Rota {
    public Aeroporto aeroportoOrigem;
    public Aeroporto aeroportoDestino;
    public String rotaMinima; //passível de mudanças

    public Rota(Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino) {

        this.aeroportoOrigem = aeroportoOrigem;
        this.aeroportoDestino = aeroportoDestino;
    }

    //to do: implementar dijkstra
}
