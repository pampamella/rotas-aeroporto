import src.config.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Teste {
        public static void main(String[] args) {
            Database MySql = new Database();
            String url = MySql.url;
            String username = MySql.username;
            String password = MySql.password;

            System.out.println(url);
            System.out.println("Connecting database...");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                System.out.println("Database connected!");
            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }
        }
}
