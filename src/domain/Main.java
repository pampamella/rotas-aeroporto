package src.domain;
import src.domain.dao.AeroportoDAO;
import src.domain.dao.RotaDAO;
import src.domain.entities.Aeroporto;
import src.domain.entities.Rota;

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
            aeroportoCF.imprimir();
            aeroportoNVT.imprimir();

            //testes rotaDAO
            Rota rotaExemplo = new Rota(aeroportoCF, aeroportoNVT);
            rotaExemplo.rotaMinima="rota exemplo";
            RotaDAO rotaDAO = new RotaDAO();
            //rotaDAO.inserirRota(rotaExemplo);
            String rotaMinima = rotaDAO.buscarRota(aeroportoCF, aeroportoNVT);

            System.out.println(rotaMinima);
            System.out.println("Deu bom");
        } catch(SQLException e) {
            throw new IllegalStateException("Erro interno!", e);
        }
    }

}

