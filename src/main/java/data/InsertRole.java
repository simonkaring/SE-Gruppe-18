package data;

import model.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static data.ConnectionDatabase.connection;

public class InsertRole {

    private static String insertRoleSQL = "Insert INTO roller (navn, type, person_id) VALUES (?,?,?)";
    private static String insertRoleElseSQL = "Insert INTO roller (navn, type) VALUES (?,?)";

    public static void insertRole(String navn, String type, Person person) {
        try {
            PreparedStatement insertStatement;
            if(person != null){
                insertStatement = connection.prepareStatement(insertRoleSQL);
                insertStatement.setString(1, navn);
                insertStatement.setString(2, type);
                insertStatement.setInt(3, person.getPersonID());
            } else {
                insertStatement = connection.prepareStatement(insertRoleElseSQL);
                insertStatement.setString(1, navn);
                insertStatement.setString(2, type);
            }
            insertStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
