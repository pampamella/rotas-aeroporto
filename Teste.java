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
        String SQL = "SELECT * FROM customers";
        PreparedStatement prepararComando = banco.prepareStatement(SQL);
        ResultSet conjuntoDados = prepararComando.executeQuery();
        System.out.println(conjuntoDados);
        while(conjuntoDados.next()){
            System.out.println(conjuntoDados.getString("city"));
        }
    }

}
