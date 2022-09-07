import src.config.Database;
import src.infra.server.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Teste {
    private Connection banco;

    public Teste() throws SQLException {
        this.banco = (new MySQLConnection()).connect();
    }
    public void listar() throws SQLException{
        String SQL = "SELECT * FROM aeroportos";
        PreparedStatement prepararComando = banco.prepareStatement(SQL);
        ResultSet conjuntoDados = prepararComando.executeQuery();
        System.out.println(conjuntoDados);
        while(conjuntoDados.next()){
            System.out.println(conjuntoDados.getString("nomeCompleto"));
        }
    }
    public void buscar(String cidade, String estado) throws SQLException{
        String SQL = "SELECT * FROM aeroportos WHERE cidade=? AND estado=?";
        PreparedStatement prepararComando = banco.prepareStatement(SQL);
        prepararComando.setString(1, cidade);
        prepararComando.setString(2, estado);
        ResultSet conjuntoDados = prepararComando.executeQuery();
        System.out.println(conjuntoDados);
        while(conjuntoDados.next()){
            System.out.println(conjuntoDados.getString("nomeCompleto"));
        }
    }

}
