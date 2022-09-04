package src.domain.entities;

public class Aeroporto {
    public String codigo;
    public String nomeCompleto;
    public String cidade;
    public String estado;
    public String latitude;
    public String longitude;

    public Aeroporto(String codigo, String nomeCompleto, String cidade, String estado, String latitude, String longitude) {
        this.codigo = codigo;
        this.nomeCompleto = nomeCompleto;
        this.cidade = cidade;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
