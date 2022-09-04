import java.sql.SQLException;

public class TesteConexao {

    public static void main(String[] args) {
        try{
            Teste test = new Teste();
           // test.listar();
            test.buscar("NYC", "NY");
            System.out.println("Deu bom");
        } catch(SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
