package src.domain;
import src.domain.dao.AeroportoDAO;
import src.domain.dao.RotaDAO;
import src.domain.model.*;
import src.domain.helpers.DistanciaMapa;

import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try{
            //testes aeroportoDAO
            AeroportoDAO aeroportoDAO = new AeroportoDAO();

            ArrayList<Aeroporto> listaAeroportos = aeroportoDAO.listarAeroportos();

            System.out.println("Insira codigo do aeroporto de origem: ");
            Scanner input = new Scanner( System.in );
            String codigoOrigem = input.nextLine();

            System.out.println("codigo origem: " + codigoOrigem);

            System.out.println("Insira codigo do aeroporto de destino: ");
            String codigoDestino = input.nextLine();

            System.out.println("codigo destino: " + codigoDestino);

            Aeroporto aeroportoOrigem = aeroportoDAO.buscaAeroportoCodigo(codigoOrigem);
            Aeroporto aeroportoDestino = aeroportoDAO.buscaAeroportoCodigo(codigoDestino);

            //teste grafo
            List<Node<Aeroporto>> allNodes = new ArrayList<>();
            for(Aeroporto aeroporto: listaAeroportos){
                Node<Aeroporto> node = new Node<>(aeroporto);
                allNodes.add(node);
            }


            //conectar todos os nos
            Graph graph = new Graph();
            allNodes = graph.conectaNodes(allNodes);

            //TODO:remover prints
            for(Node<Aeroporto> node: allNodes){
                System.out.println(node.getValue().getCodigo());
                System.out.println("Adjacentes");
                for (Map.Entry<Node<Aeroporto>, Double> entry : node.getAdjacentNodes().entrySet()) {
                System.out.println(entry.getKey().getValue().getCodigo());
                }
            }

            //remover aresta entre origem e destino

            Node<Aeroporto> origem = new Node<>(aeroportoOrigem);
            Node<Aeroporto> destino = new Node<>(aeroportoDestino);

            allNodes = graph.desconectaOrigemDestino(allNodes, origem, destino);

            //TODO:remover prints
            for(Node<Aeroporto> node: allNodes){
                System.out.println(node.getValue().getCodigo());
                System.out.println("Adjacentes");
                for (Map.Entry<Node<Aeroporto>, Double> entry : node.getAdjacentNodes().entrySet()) {
                    System.out.println(entry.getKey().getValue().getCodigo());
                }
            }

            //corrigindo a droga da origemmm
            for(Node<Aeroporto> node: allNodes){
                if(Objects.equals(node.getValue().getCodigo(), origem.getValue().getCodigo())){ //TODO:refatorar
                    origem.setAdjacentNodes(node.getAdjacentNodes());
                }
            }




            //adicionar nos ao o grafo

            for(Node<Aeroporto> node: allNodes){
                graph.addNode(node);
            }
            Dijkstra.calculateShortestPathFromSource(graph, origem);



            //criar rota
            Rota rotaExemplo = new Rota(origem.getValue(), destino.getValue());

            rotaExemplo.encontraRotaMinima(graph, destino);
            RotaDAO rotaDAO = new RotaDAO();
            rotaDAO.inserirRota(rotaExemplo);

            //recupera aresta entre origem e destino
            graph.reconectaOrigemDestino(allNodes, origem, destino);


            System.out.println("Deu bom");
        } catch(SQLException e) {
            throw new IllegalStateException("Erro interno!", e);
        }
    }

}

