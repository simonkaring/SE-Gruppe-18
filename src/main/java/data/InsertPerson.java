package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static data.ConnectionDatabase.connection;

public class InsertPerson {

    private static String insertPersonSQL = "Insert INTO personer (fornavn, efternavn, nationalitet, dag, maaned, aar) VALUES (?,?,?,?,?,?)";

    public static void insertPerson(String firstname, String lastname, String nationality, int day, int month, int year) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(insertPersonSQL);
            insertStatement.setString(1, firstname);
            insertStatement.setString(2, lastname);
            insertStatement.setString(3, nationality);
            insertStatement.setInt(4, day);
            insertStatement.setInt(5, month);
            insertStatement.setInt(6, year);
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
