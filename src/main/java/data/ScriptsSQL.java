package data;

import model.Person;
import model.Producent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static data.ConnectionDatabase.connection;

public class ScriptsSQL {

    private static String deleteSQL = "DELETE FROM programmer WHERE navn=?";
    public static void deleteProduction(String production) {

        try {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
            deleteStatement.setString(1, production);
            deleteStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    private static String insertProducerSQL = "Insert INTO producenter (navn) VALUES (?)";
    public static void insertProducer(String navn) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(insertProducerSQL);
            insertStatement.setString(1, navn);
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String insertProductionSQL = "Insert INTO programmer (navn, producent_id) VALUES (?,?)";
    public static void insertProduction(String productionTitle, Producent production){
        try {
            PreparedStatement insertStatement = connection.prepareStatement(insertProductionSQL);
            insertStatement.setString(1, productionTitle);
            insertStatement.setInt(2, production.getProducentID());
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    boolean isLoginInformationCorrect(String username, String password) {
        boolean isLoginInformationCorrectBoolean = false;
        return isLoginInformationCorrectBoolean;
    }

    boolean askToRegister() {
        return false;
    }
}
