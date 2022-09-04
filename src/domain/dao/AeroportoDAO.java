package src.domain.dao;
import src.infra.server.MySQLConnection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import src.domain.entities.Aeroporto;

public class AeroportoDAO {
    private Connection banco;

    public AeroportoDAO() throws SQLException {
        this.banco = (new MySQLConnection()).connect();
    }

    public ArrayList listarAeroportos() throws SQLException{
        String SQL = "SELECT * FROM aeroportos";
        Aeroporto aeroporto;
        ArrayList aeroportosInternacionais = new ArrayList();

        PreparedStatement prepararComando = banco.prepareStatement(SQL);

        ResultSet conjuntoDados = prepararComando.executeQuery();

        while(conjuntoDados.next()){
            aeroporto = new Aeroporto(
                    conjuntoDados.getString("codigo"),
                    conjuntoDados.getString("nomeCompleto"),
                    conjuntoDados.getString("cidade"),
                    conjuntoDados.getString("estado"),
                    conjuntoDados.getString("latitude"),
                    conjuntoDados.getString("longitude")
            );
            aeroportosInternacionais.add(aeroporto);
        }

        return aeroportosInternacionais;
    }
}
