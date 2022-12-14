package domain.helpers;

import domain.model.Aeroporto;

public class DistanciaMapa {
    public static double calculaDistancia(Aeroporto origem, Aeroporto destino)
    {
        double latOrigem = Math.toRadians(origem.getLatitude());
        double lonOrigem = Math.toRadians(origem.getLongitude());
        double latDestino = Math.toRadians(destino.getLatitude());
        double lonDestino = Math.toRadians(destino.getLongitude());

        // Fórmula de Haversine
        double distanciaLon = lonDestino - lonOrigem;
        double distanciaLat = latDestino - latOrigem;
        double aux = Math.pow(Math.sin(distanciaLat / 2), 2)
                + Math.cos(latOrigem) * Math.cos(latDestino)
                * Math.pow(Math.sin(distanciaLon / 2),2);

        double ang = 2 * Math.asin(Math.sqrt(aux));
        double raioTerra = 6371;

        return(ang * raioTerra);
    }
}
