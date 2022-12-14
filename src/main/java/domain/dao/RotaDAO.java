package domain.dao;
import infra.server.MySQLConnection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import domain.model.Rota;

public class RotaDAO {
    private Connection banco;

    public RotaDAO() throws SQLException {
        this.banco = (new MySQLConnection()).connect();
    }
    public int inserirRota(Rota rota) throws SQLException{
        String SQL = "INSERT INTO rotas (`codigoOrigem`, `codigoDestino`, `nomeOrigem`, `nomeDestino`, `rotaMinima`) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement prepararComando = banco.prepareStatement(SQL);

        prepararComando.setString(1, rota.getAeroportoOrigem().getCodigo());
        prepararComando.setString(2, rota.getAeroportoDestino().getCodigo());
        prepararComando.setString(3, rota.getAeroportoOrigem().getNomeCompleto());
        prepararComando.setString(4, rota.getAeroportoDestino().getNomeCompleto());
        prepararComando.setString(5, rota.getRotaMinima());

        int resultado = prepararComando.executeUpdate();

        //Retornar a quantidade de registros alterados no banco
        return resultado;
    }
}