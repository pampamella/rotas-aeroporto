package src.domain.dao;
import src.domain.model.Aeroporto;
import src.infra.server.MySQLConnection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import src.domain.model.Rota;

public class RotaDAO {
    private Connection banco;

    public RotaDAO() throws SQLException {
        this.banco = (new MySQLConnection()).connect();
    }
    public int inserirRota(Rota rota) throws SQLException{
        String SQL = "INSERT INTO rotas (`codigoOrigem`, `codigoDestino`, `nomeOrigem`, `nomeDestino`, `rotaMinima`) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement prepararComando = banco.prepareStatement(SQL);

        prepararComando.setString(1, rota.aeroportoOrigem.codigo);
        prepararComando.setString(2, rota.aeroportoDestino.codigo);
        prepararComando.setString(3, rota.aeroportoOrigem.nomeCompleto);
        prepararComando.setString(4, rota.aeroportoDestino.nomeCompleto);
        prepararComando.setString(5, rota.rotaMinima);

        int resultado = prepararComando.executeUpdate();

        //Retornar a quantidade de registros alterados no banco
        return resultado;
    }
    public String buscarRota(Aeroporto origem, Aeroporto destino) throws SQLException {
        String SQL = "SELECT * FROM rotas WHERE codigoOrigem=? AND codigoDestino=?";
        PreparedStatement prepararComando = banco.prepareStatement(SQL);
        prepararComando.setString(1, origem.codigo);
        prepararComando.setString(2, destino.codigo);
        ResultSet conjuntoDados = prepararComando.executeQuery();
        conjuntoDados.next();
        return conjuntoDados.getString("rotaMinima");
    }
}
