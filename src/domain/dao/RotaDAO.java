package src.domain.dao;
import src.infra.server.MySQLConnection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import src.domain.entities.Rota;

public class RotaDAO {
    private Connection banco;

    public RotaDAO() throws SQLException {
        this.banco = (new MySQLConnection()).connect();
    }
    public int inserirRota(Rota rota) throws SQLException{
        String SQL = "INSERT INTO rotas (`codOrigem`, `codDestino`, `rotaMinima`) VALUES (?, ?, ?)";

        PreparedStatement prepararComando = banco.prepareStatement(SQL);

        prepararComando.setString(1, rota.codOrigem);
        prepararComando.setString(2, rota.codDestino);
        prepararComando.setString(3, rota.rotaMinima);

        int resultado = prepararComando.executeUpdate();

        //Retornar a quantidade de registros alterados no banco
        return resultado;
    }
    public String buscarRota(String codOrigem, String codDestino) throws SQLException {
        String SQL = "SELECT * FROM rotas WHERE codOrigem=? AND codDestino=?";
        PreparedStatement prepararComando = banco.prepareStatement(SQL);
        prepararComando.setString(1, codOrigem);
        prepararComando.setString(2, codDestino);
        ResultSet conjuntoDados = prepararComando.executeQuery();
        return conjuntoDados.getString("rotaMinima");
    }
}
