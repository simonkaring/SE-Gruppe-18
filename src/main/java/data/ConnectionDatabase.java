package data;

import model.Person;
import model.Producent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionDatabase {

    public static java.sql.Connection connection = null;
    private static ConnectionDatabase instance;
    private static String username="postgres";
    private static String password="root";
    private static String url="jdbc:postgresql://localhost:5432/TV2";

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
            this.connection = DriverManager.getConnection(url, username, password);
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

    public static void indsaetProducent(String navn) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("Insert INTO producenter (navn) VALUES (?)");
            insertStatement.setString(1, navn);
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void indsaetProgram(String navn, Producent producent) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("Insert INTO programmer (navn, producent_id) VALUES (?,?)");
            insertStatement.setString(1, navn);
            insertStatement.setInt(2, producent.getProducentID());
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void indsaetPerson(String fornavn, String efternavn, String nationalitet, int dag, int maaned, int aar){
        try {
            PreparedStatement insertStatement = connection.prepareStatement("Insert INTO personer (fornavn, efternavn, nationalitet, dag, maaned, aar) VALUES (?,?,?,?,?,?)");
            insertStatement.setString(1, fornavn);
            insertStatement.setString(2, efternavn);
            insertStatement.setString(3, nationalitet);
            insertStatement.setInt(4, dag);
            insertStatement.setInt(5, maaned);
            insertStatement.setInt(6, aar);
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void indsaetRolle(String navn, String type, Person person){
        try {
            PreparedStatement insertStatement;
            if(person != null){
                insertStatement = connection.prepareStatement("Insert INTO roller (navn, type, person_id) VALUES (?,?,?)");
                insertStatement.setString(1, navn);
                insertStatement.setString(2, type);
                insertStatement.setInt(3, person.getPersonID());
            } else {
                insertStatement = connection.prepareStatement("Insert INTO roller (navn, type) VALUES (?,?)");
                insertStatement.setString(1, navn);
                insertStatement.setString(2, type);
            }
            insertStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

