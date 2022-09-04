package src.domain.entities;

public class Rota {
    public String codOrigem;
    public String codDestino;
    public Aeroporto aeroportoOrigem;
    public Aeroporto aeroportoDestino;
    public String rotaMinima; //passível de mudanças

    public Rota(String codOrigem, String codDestino, Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino) {
        this.codOrigem = codOrigem;
        this.codDestino = codDestino;
        this.aeroportoOrigem = aeroportoOrigem;
        this.aeroportoDestino = aeroportoDestino;
    }

    //to do: implementar dijkstra
}
