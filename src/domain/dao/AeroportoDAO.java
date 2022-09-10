package src.domain.dao;
import src.infra.server.MySQLConnection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import src.domain.model.Aeroporto;

public class AeroportoDAO {
    private Connection banco;

    public AeroportoDAO() throws SQLException {
        this.banco = (new MySQLConnection()).connect();
    }

    public ArrayList<Aeroporto> listarAeroportos() throws SQLException {
        String SQL = "SELECT * FROM aeroportos";
        Aeroporto aeroporto;
        ArrayList<Aeroporto> aeroportosInternacionais = new ArrayList<>();

        PreparedStatement prepararComando = banco.prepareStatement(SQL);

        ResultSet conjuntoDados = prepararComando.executeQuery();

        while (conjuntoDados.next()) {
            aeroporto = new Aeroporto(
                    conjuntoDados.getString("codigo"),
                    conjuntoDados.getString("nomeCompleto"),
                    conjuntoDados.getString("cidade"),
                    conjuntoDados.getString("estado"),
                    conjuntoDados.getDouble("latitude"),
                    conjuntoDados.getDouble("longitude")
            );
            aeroportosInternacionais.add(aeroporto);
        }

        return aeroportosInternacionais;
    }

    public Aeroporto buscaCidadeEstado(String cidade, String estado) throws SQLException {
        String SQL = "SELECT * FROM aeroportos WHERE cidade=? AND estado=?";
        PreparedStatement prepararComando = banco.prepareStatement(SQL);
        prepararComando.setString(1, cidade);
        prepararComando.setString(2, estado);
        ResultSet conjuntoDados = prepararComando.executeQuery();

       Aeroporto aeroporto = null;

        while (conjuntoDados.next()) {
            aeroporto = new Aeroporto(
                    conjuntoDados.getString("codigo"),
                    conjuntoDados.getString("nomeCompleto"),
                    cidade,
                    estado,
                    conjuntoDados.getDouble("latitude"),
                    conjuntoDados.getDouble("longitude")
            );
        }
        return aeroporto;
    }

    public ArrayList<Aeroporto> buscaEstado(String estado) throws SQLException {
        String SQL = "SELECT * FROM aeroportos WHERE estado=?";
        PreparedStatement prepararComando = banco.prepareStatement(SQL);
        prepararComando.setString(1, estado);
        ResultSet conjuntoDados = prepararComando.executeQuery();

        Aeroporto aeroporto;
        ArrayList<Aeroporto> aeroportosPorEstado = new ArrayList<>();
        while (conjuntoDados.next()) {
            aeroporto = new Aeroporto(
                    conjuntoDados.getString("codigo"),
                    conjuntoDados.getString("nomeCompleto"),
                    conjuntoDados.getString("cidade"),
                    conjuntoDados.getString("estado"),
                    conjuntoDados.getDouble("latitude"),
                    conjuntoDados.getDouble("longitude")
            );
            aeroportosPorEstado.add(aeroporto);
        }
        return aeroportosPorEstado;
    }

}
