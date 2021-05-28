package model;

import data.ConnectionDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Rolle extends ConnectionDatabase {

    private int rolleID;
    private String navn;
    private String type;
    private Person spillesAf;

    //Afgør rækkefølgen krediteringen bliver udskrevet. Kan evt hardcodes.
    private static List<String> rolleTyper = new ArrayList<>();

    public Rolle(String navn, String type){
        indsaetRolle(navn, type, null);
        try{
            int id = 0;
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM roller ORDER BY id DESC LIMIT 1");
            ResultSet queryResultSet = queryStatement.executeQuery();
            while(queryResultSet.next()){
                id = queryResultSet.getInt("id");
            }
            this.rolleID = id;
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.navn = navn;
        this.type = type;
        KrediteringSystem.getSamletRoller().add(this);
    }

    public Rolle(String navn, String type, Person spillesAF){
        indsaetRolle(navn, type, spillesAF);
        try{
            int id = 0;
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM roller ORDER BY id DESC LIMIT 1");
            ResultSet queryResultSet = queryStatement.executeQuery();
            while(queryResultSet.next()){
                id = queryResultSet.getInt("id");
            }
            this.rolleID = id;
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.navn = navn;
        this.type = type;
        this.spillesAf = spillesAF;
        KrediteringSystem.getSamletRoller().add(this);
    }

    public Rolle(String navn, String type, int rolleID){
        this.rolleID = rolleID;
        this.navn = navn;
        this.type = type;
        KrediteringSystem.getSamletRoller().add(this);
    }

    public Rolle(String navn, String type, Person spillesAF, int rolleID){
        this.rolleID = rolleID;
        this.navn = navn;
        this.type = type;
        this.spillesAf = spillesAF;
        KrediteringSystem.getSamletRoller().add(this);
    }

    //Tilknyt person til rollen.
    public void tilknytPersonTilRolle(Person person){
        try {
            PreparedStatement insertStatement = connection.prepareStatement("UPDATE roller SET person_id = ? WHERE id = ?");
            insertStatement.setInt(1, person.getPersonID());
            insertStatement.setInt(1, this.rolleID);
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.spillesAf = person;
    }

    //Fjern person fra rollen.
    public void fjernPersonFraRolle(Person person){
        if(this.spillesAf.equals(person)){
            try {
                PreparedStatement insertStatement = connection.prepareStatement("UPDATE roller SET person_id = null WHERE id = ?");
                insertStatement.setInt(1, this.rolleID);
                insertStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.spillesAf = null;
        }
    }

    //Hvis rollen ikke er udfyldt af en person, vil den udskrive rollens navn, og "mangler" rolletypen.
    //Ellers udskriver den rollens navn og hvem der spiller rollen.
    @Override
    public String toString(){
        if(spillesAf == null){
            return navn + " : mangler " + type;
        } else {
            return navn + " : " + spillesAf;
        }
    }

    //Hvis der er en ny rolle type, vil den blive lagt ind i den statiske liste "rolleTyper".
    public static void addRolleType(Rolle rolle){
        for(String rolleType : rolleTyper){
            if(rolleType.equals(rolle.getType())){
                return;
            }
        }
        rolleTyper.add(rolle.getType());
    }

    //Gettere og settere

    public int getRolleID() {
        return rolleID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        try{
            PreparedStatement insertStatement = connection.prepareStatement("UPDATE roller SET navn = ? WHERE id = ?");
            insertStatement.setString(1, navn);
            insertStatement.setInt(2, this.rolleID);
            insertStatement.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.navn = navn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        try{
            PreparedStatement insertStatement = connection.prepareStatement("UPDATE roller SET type = ? WHERE id = ?");
            insertStatement.setString(1, type);
            insertStatement.setInt(2, this.rolleID);
            insertStatement.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.type = type;
    }

    public Person getSpillesAf() {
        return spillesAf;
    }

    public void setSpillesAf(Person spillesAf) {
        try{
            PreparedStatement insertStatement = connection.prepareStatement("UPDATE roller SET person_id = ? WHERE id = ?");
            insertStatement.setInt(1, spillesAf.getPersonID());
            insertStatement.setInt(2, this.rolleID);
            insertStatement.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.spillesAf = spillesAf;
    }

    public static List<String> getRolleTyper() {
        return rolleTyper;
    }

}
