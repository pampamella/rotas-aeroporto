package src.domain.model;

public class Aeroporto {
    private String codigo;
    private String nomeCompleto;
    private String cidade;
    private String estado;
    private Double latitude;
    private Double longitude;

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

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
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
