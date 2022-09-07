package src.domain;
import src.domain.dao.AeroportoDAO;
import src.domain.dao.RotaDAO;
import src.domain.entities.Aeroporto;
import src.domain.entities.Rota;
import src.domain.helpers.DistanciaMapa;

import java.sql.SQLException;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try{
            //testes aeroportoDAO
            AeroportoDAO aeroportoDAO = new AeroportoDAO();
//            ArrayList<Aeroporto> aeroportosPorEstado = aeroportoDAO.buscaEstado("Rio Grande do Sul");
//            aeroportosPorEstado.forEach(Aeroporto::imprimir);
//            ArrayList<Aeroporto> listaAeroportos = aeroportoDAO.listarAeroportos();
//            listaAeroportos.forEach(Aeroporto::imprimir);
            Aeroporto aeroportoCF = aeroportoDAO.buscaCidadeEstado("Cabo Frio", "Rio de Janeiro");
            Aeroporto aeroportoNVT = aeroportoDAO.buscaCidadeEstado("Navegantes", "Santa Catarina");
            Aeroporto aeroportoGIG = aeroportoDAO.buscaCidadeEstado("Rio de Janeiro", "Rio de Janeiro");
            aeroportoCF.imprimir();
            aeroportoNVT.imprimir();

            //testes rotaDAO
            Rota rotaExemplo = new Rota(aeroportoCF, aeroportoNVT);
            rotaExemplo.rotaMinima="rota exemplo";
            RotaDAO rotaDAO = new RotaDAO();
            //rotaDAO.inserirRota(rotaExemplo);
            String rotaMinima = rotaDAO.buscarRota(aeroportoCF, aeroportoNVT);

            System.out.println(rotaMinima);
            //testes distancia
            DistanciaMapa helper = new DistanciaMapa();
            Double distancia = helper.calculaDistancia(aeroportoCF, aeroportoGIG);
            System.out.println(distancia);

            System.out.println("Deu bom");
        } catch(SQLException e) {
            throw new IllegalStateException("Erro interno!", e);
        }
    }

}

