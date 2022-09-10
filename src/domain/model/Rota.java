package src.domain.model;

public class Rota {
    public Aeroporto aeroportoOrigem;
    public Aeroporto aeroportoDestino;
    public String rotaMinima;

    public Rota(Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino) {

        this.aeroportoOrigem = aeroportoOrigem;
        this.aeroportoDestino = aeroportoDestino;
    }

}
