package data;

import model.Person;
import model.Producent;
import model.Program;
import model.Rolle;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ConnectionDatabase {

    public static java.sql.Connection connection = null;
    public static String host="localhost";
    public static String port="5432";
    public static String db_name="TV2";
    public static String username="postgres";
    public static String password="root";

    public static void main(String[] args) {
        opretForbindelse();
        Producent produ = new Producent("TESTER");
        System.out.println("Navn: " + produ.getNavn() + " ID: " + produ.getProducentID());
        Program program1 = new Program("Test 1 - program", produ);
        Program program2 = new Program("Test 2 - program", produ);
        Person person1 = new Person("Helle1","Kristensen", LocalDate.of(1965, 11, 30),"Tyskland");
        Person person2 = new Person("Helle2","Kristensen", LocalDate.of(1988, 1, 25),"Danmark");
        Rolle rolle1 = new Rolle("THOMAS", "test", null);
        Rolle rolle2 = new Rolle("PETER", "test", person1);
        Rolle rolle3 = new Rolle("LONE", "test", person2);
    }

    public static void opretForbindelse(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"", ""+username+"", ""+password+"");
            if (connection != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void indsaetProducent(String navn){
        try {
            PreparedStatement insertStatement = connection.prepareStatement("Insert INTO producenter (navn) VALUES (?)");
            insertStatement.setString(1, navn);
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void indsaetProgram(String navn, Producent producent){
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

