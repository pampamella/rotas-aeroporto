package domain.model;


public class Aeroporto {
    private final String codigo;
    private final String nomeCompleto;
    private final String cidade;
    private final String estado;
    private final Double latitude;
    private final Double longitude;

    public Aeroporto(String codigo, String nomeCompleto, String cidade, String estado, Double latitude, Double longitude) {
        this.codigo = codigo;
        this.nomeCompleto = nomeCompleto;
        this.cidade = cidade;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
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
