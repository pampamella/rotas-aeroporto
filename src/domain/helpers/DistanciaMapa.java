package src.domain.helpers;

import src.domain.entities.Aeroporto;

public class DistanciaMapa {
    public static double calculaDistancia(Aeroporto origem, Aeroporto destino)
    {
        double latOrigem = origem.latitude;
        double lonOrigem = origem.longitude;

        double latDestino = destino.latitude;
        double lonDestino = destino.longitude;

        latOrigem = Math.toRadians(latOrigem);
        lonOrigem = Math.toRadians(lonOrigem);
        latDestino = Math.toRadians(latDestino);
        lonDestino = Math.toRadians(lonDestino);

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
