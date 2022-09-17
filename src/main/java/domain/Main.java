package domain;
import domain.dao.AeroportoDAO;
import domain.dao.RotaDAO;
import domain.helpers.Tabela;
import domain.model.*;
import tech.tablesaw.api.Table;

import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try{

            AeroportoDAO aeroportoDAO = new AeroportoDAO();
            RotaDAO rotaDAO = new RotaDAO();

            //Busca dados dos aeroportos no banco
            ArrayList<Aeroporto> listaAeroportos = aeroportoDAO.listarAeroportos();

            Table tabelaAeroportos = Tabela.montaTabela(listaAeroportos);

            Scanner input = new Scanner( System.in );

            while(true){
                System.out.println("Deseja fazer nova busca? (S/N) ");
                String resposta = input.nextLine();
                if(Objects.equals(resposta, "N") || !Objects.equals(resposta, "S")){
                    break;
                }
                System.out.println(tabelaAeroportos.print(40));

                System.out.println("Deseja fazer busca por estado? (S/N) ");
                resposta = input.nextLine();
                if(Objects.equals(resposta, "S")){
                    System.out.println("Nome do estado: ");
                    resposta = input.nextLine();
                    //Busca dados de aeroporto para estado específico
                    ArrayList<Aeroporto> listaPorEstado = aeroportoDAO.buscaEstado(resposta);
                    Table tabelaAeroEstado = Tabela.montaTabela(listaPorEstado);
                    System.out.println(tabelaAeroEstado.print(10));
                }
                System.out.println("Deseja fazer busca por cidade e estado? (S/N) ");
                resposta = input.nextLine();
                if(Objects.equals(resposta, "S")){
                    System.out.println("Nome do estado: ");
                    String estado = input.nextLine();
                    System.out.println("Nome da cidade: ");
                    String cidade = input.nextLine();
                    ArrayList<Aeroporto> listaPorCidade = new ArrayList<>();
                    //Busca dados de aeroporto para cidade e estado específicos
                    listaPorCidade.add(aeroportoDAO.buscaCidadeEstado(cidade, estado));
                    Table tabelaAeroCidade = Tabela.montaTabela(listaPorCidade);
                    System.out.println(tabelaAeroCidade.print(1));
                }

                System.out.println("Insira código do aeroporto de origem: ");
                String codigoOrigem = input.nextLine();
                System.out.println("Código origem: " + codigoOrigem);

                System.out.println("Insira código do aeroporto de destino: ");
                String codigoDestino = input.nextLine();
                System.out.println("Código destino: " + codigoDestino);
                //Busca dados do aeroporto no banco
                Aeroporto aeroportoOrigem = aeroportoDAO.buscaAeroportoCodigo(codigoOrigem);
                Aeroporto aeroportoDestino = aeroportoDAO.buscaAeroportoCodigo(codigoDestino);

                //Adiciona cada aeroporto a um nó
                List<Node<Aeroporto>> allNodes = new ArrayList<>();
                for(Aeroporto aeroporto: listaAeroportos){
                    Node<Aeroporto> node = new Node<>(aeroporto);
                    allNodes.add(node);
                }

                //Conecta todos os nós
                Graph graph = new Graph();
                allNodes = graph.conectaNodes(allNodes);

                //Remove aresta entre origem e destino
                Node<Aeroporto> origem = new Node<>(aeroportoOrigem);
                Node<Aeroporto> destino = new Node<>(aeroportoDestino);

                allNodes = graph.desconectaOrigemDestino(allNodes, origem, destino);

                //Copia lista de adjacência do nó de origem da lista para o nó origem criado
                for(Node<Aeroporto> node: allNodes){
                    if(Objects.equals(node.getValue().getCodigo(), origem.getValue().getCodigo())){
                        origem.setAdjacentNodes(node.getAdjacentNodes());
                    }
                }

                //Adiciona nós ao grafo
                for(Node<Aeroporto> node: allNodes){
                    graph.addNode(node);
                }
                //Implementa dijkstra
                Dijkstra.calculateShortestPathFromSource(graph, origem);

                //Cria objeto rota
                Rota rotaExemplo = new Rota(origem.getValue(), destino.getValue());

                //Utiliza resultado do dijkstra para encontrar rota minima desejada
                rotaExemplo.encontraRotaMinima(graph, destino);

                //Insere nova rota ao banco
                rotaDAO.inserirRota(rotaExemplo);

                //Recupera aresta entre origem e destino
                graph.reconectaOrigemDestino(allNodes, origem, destino);
            }

            System.out.println("Busca finalizada");
        } catch(SQLException e) {
            throw new IllegalStateException("Erro interno!", e);
        }
    }

}

