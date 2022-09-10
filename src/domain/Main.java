package src.domain;
import src.domain.dao.AeroportoDAO;
import src.domain.dao.RotaDAO;
import src.domain.model.*;
import src.domain.helpers.DistanciaMapa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        try{
            //testes aeroportoDAO
            AeroportoDAO aeroportoDAO = new AeroportoDAO();
            ArrayList<Aeroporto> aeroportosPorEstado = aeroportoDAO.buscaEstado("Rio Grande do Sul");
//            aeroportosPorEstado.forEach(Aeroporto::imprimir);
            ArrayList<Aeroporto> listaAeroportos = aeroportoDAO.listarAeroportos();
//            listaAeroportos.forEach(Aeroporto::imprimir);
            Aeroporto aeroportoCF = aeroportoDAO.buscaCidadeEstado("Cabo Frio", "Rio de Janeiro");
            Aeroporto aeroportoNVT = aeroportoDAO.buscaCidadeEstado("Navegantes", "Santa Catarina");
            Aeroporto aeroportoGIG = aeroportoDAO.buscaCidadeEstado("Rio de Janeiro", "Rio de Janeiro");
            Aeroporto aeroportoPOA = aeroportoDAO.buscaCidadeEstado("Porto Alegre", "Rio Grande do Sul");
            Aeroporto aeroportoPET = aeroportoDAO.buscaCidadeEstado("Pelotas", "Rio Grande do Sul");

            //teste grafo
            List<Node<Aeroporto>> allNodes = new ArrayList<>();
            for(Aeroporto aeroporto: listaAeroportos){
                Node<Aeroporto> node = new Node<>(aeroporto);
                allNodes.add(node);
            }
            Node<Aeroporto> nodeA = new Node<>(aeroportoCF);
            Node<Aeroporto> nodeB = new Node<>(aeroportoGIG);
            Node<Aeroporto> nodeC = new Node<>(aeroportoNVT);
//
//            allNodes.forEach(no->no.getValue().imprimir());

            DistanciaMapa helper = new DistanciaMapa();
            Double distance=0.0;
            //Arestas grafo completo
            for(Node<Aeroporto> node: allNodes){
                for (Node<Aeroporto> nodeIncident:allNodes){
                    distance = helper.calculaDistancia(node.getValue(), nodeIncident.getValue());
                    node.addDestination(nodeIncident, distance);
                }
            }

            for(Node<Aeroporto> node: allNodes){
                System.out.println(node.getValue().codigo);
                System.out.println("Adjacentes");
                for (Map.Entry<Node<Aeroporto>, Double> entry : node.getAdjacentNodes().entrySet()) {
                System.out.println(entry.getKey().getValue().codigo + ":" + entry.getValue());
                }
            }

            //remover aresta entre origem e destino

            Node<Aeroporto> origem = new Node<>(aeroportoPET);
            Node<Aeroporto> destino = new Node<>(aeroportoPOA);

            for(Node<Aeroporto> node: allNodes){
                System.out.println(node.getValue().codigo);
                System.out.println("Removendo");
                if(Objects.equals(node.getValue().codigo, origem.getValue().codigo) || Objects.equals(node.getValue().codigo, destino.getValue().codigo)){ //TODO:refatorar samerda
                    node.getAdjacentNodes().entrySet().removeIf(i -> Objects.equals(i.getKey().getValue().codigo, origem.getValue().codigo));
                    node.getAdjacentNodes().entrySet().removeIf(i -> Objects.equals(i.getKey().getValue().codigo, destino.getValue().codigo));
                }
            }

            for(Node<Aeroporto> node: allNodes){
                System.out.println(node.getValue().codigo);
                System.out.println("Adjacentes");
                for (Map.Entry<Node<Aeroporto>, Double> entry : node.getAdjacentNodes().entrySet()) {
                    System.out.println(entry.getKey().getValue().codigo + ":" + entry.getValue());
                }
            }
//TODO:lembrar de recuperar aresta removida

            //corrigindo a droga da origemmm
            for(Node<Aeroporto> node: allNodes){
                if(Objects.equals(node.getValue().codigo, origem.getValue().codigo)){ //TODO:refatorar samerda
                    origem.setAdjacentNodes(node.getAdjacentNodes());
                   // node.getAdjacentNodes().entrySet().removeIf(i -> Objects.equals(i.getKey().getValue().codigo, origem.getValue().codigo));
                }
            }



            //criar o grafo

            Graph graph = new Graph();
            for(Node<Aeroporto> node: allNodes){
                graph.addNode(node);
            }
            System.out.println("TROPA DA XUSTEL");
            graph = Dijkstra.calculateShortestPathFromSource(graph, origem);

            //pegar shortest paths pra rota minima

            StringBuilder rotaMinima = new StringBuilder();

            for(Node<Aeroporto> node : graph.getNodes()){
                if(Objects.equals(node.getValue().codigo, destino.getValue().codigo)){
                    System.out.print(node.getValue().codigo + " shortest path ");
                    System.out.println();
                    for(Node<Aeroporto> no: node.getShortestPath()){
                        rotaMinima.append(no.getValue().codigo).append(" - ");
                        System.out.print(no.getValue().codigo);
                    }
                }

                System.out.println();
            }

            rotaMinima.append(destino.getValue().codigo);
            System.out.println(rotaMinima.toString());



            //testes rotaDAO
            Rota rotaExemplo = new Rota(origem.getValue(), destino.getValue());
            rotaExemplo.rotaMinima = rotaMinima.toString();
            RotaDAO rotaDAO = new RotaDAO();
            rotaDAO.inserirRota(rotaExemplo);
//            String rotaMinima = rotaDAO.buscarRota(aeroportoCF, aeroportoNVT);
//
//            System.out.println(rotaMinima);


            System.out.println("Deu bom");
        } catch(SQLException e) {
            throw new IllegalStateException("Erro interno!", e);
        }
    }

}

