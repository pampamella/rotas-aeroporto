package config;

public class Database {

    private final String baseUrl = "jdbc:mysql://localhost:";
    private final String port = "3306";
    private final String database = "labprog";
    final String username = "java";
    final String password = "senhamysql";

    private final String urlConnection;

    public Database() {
        this.urlConnection = this.baseUrl + this.port + "/" + this.database;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrlConnection() {
        return urlConnection;
    }
}
