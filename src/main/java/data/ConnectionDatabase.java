package data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionDatabase {

    public static java.sql.Connection connection = null;
    private static ConnectionDatabase instance;
    private static final String username = "postgres";
    private static final String password = "root";
    private static final String url = "jdbc:postgresql://localhost:5432/TV2";

    public static void opretForbindelse() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void DatabaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionDatabase getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionDatabase();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConnectionDatabase();
        }
        return instance;
    }
}

