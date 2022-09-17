package infra.server;

import config.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    String url;
    String username;
    String password;

    public MySQLConnection(){
        Database MySql = new Database();
            this.url = MySql.getUrlConnection();
            this.username = MySql.getUsername();
            this.password = MySql.getPassword();
    }

    public Connection connect() {
        try {
            return (Connection) DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

}
