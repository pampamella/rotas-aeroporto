package src.domain.model;

public class Aeroporto {
    public String codigo;
    public String nomeCompleto;
    public String cidade;
    public String estado;
    public Double latitude;
    public Double longitude;

    public Aeroporto(String codigo, String nomeCompleto, String cidade, String estado, Double latitude, Double longitude) {
        this.codigo = codigo;
        this.nomeCompleto = nomeCompleto;
        this.cidade = cidade;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void imprimir(){
        System.out.println(" ---- Aeroporto ----");
        System.out.print("Codigo: " + codigo + " | ");
        System.out.print("Nome: " + nomeCompleto + " | ");
        System.out.print("Cidade: " + cidade + " | ");
        System.out.print("Estado: " + estado + " | ");
        System.out.print("Latitude: " + latitude + " | ");
        System.out.println("Longitude: " + longitude);
    }
}
