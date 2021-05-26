package model;

import data.ConnectionDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Program extends ConnectionDatabase {

    private int programID;
    private String titel;
    private List<Rolle> rollerIProgram;

    public Program(String titel, Producent producent){
        indsaetProgram(titel, producent);
        try{
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM producenter");
            ResultSet queryResultSet = queryStatement.executeQuery();
            while(queryResultSet.next()){
                new Producent(queryResultSet.getString("navn"), queryResultSet.getInt("id"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.titel = titel;
        this.rollerIProgram = new ArrayList<>();
        KrediteringSystem.getSamletProgrammer().add(this);
    }

    public Program(String titel, int programID){
        this.programID = programID;
        this.titel = titel;
        this.rollerIProgram = new ArrayList<>();
        KrediteringSystem.getSamletProgrammer().add(this);
    }


    //Laver rolle i programmet i rollerIProgram-listen, uden at tilknytte person til rollen.
    public void addRolle(String navn, String type){
        for(Rolle rolleIProgram : getRollerIProgram()){
            if(rolleIProgram.getNavn().equals(navn) && rolleIProgram.getType().equals(type)){
                return;
            }
        }
        Rolle rolle = new Rolle(navn, type);
        rollerIProgram.add(rolle);
        Rolle.addRolleType(rolle);
        try {
            PreparedStatement insertStatement = connection.prepareStatement("Insert INTO program_rolle (program_id, rolle_id) VALUES (?,?)");
            insertStatement.setInt(1, this.programID);
            insertStatement.setInt(2, rolle.getRolleID());
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Laver rolle i programmet i rollerIProgram-listen, og tilknytter person til rollen.
    public void addRolle(String navn, String type, Person person){
        for(Rolle rolleIProgram : getRollerIProgram()){
            if(rolleIProgram.getNavn().equals(navn) && rolleIProgram.getType().equals(type)){
                return;
            }
        }
        Rolle rolle = new Rolle(navn, type, person);
        rollerIProgram.add(rolle);
        Rolle.addRolleType(rolle);
        try {
            PreparedStatement insertStatement = connection.prepareStatement("Insert INTO program_rolle (program_id, rolle_id) VALUES (?,?)");
            insertStatement.setInt(1, this.programID);
            insertStatement.setInt(2, rolle.getRolleID());
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Fjerner den valgte rolle fra programmet.
    public void fjernRolle(Rolle rolle){
        try {
            PreparedStatement insertStatement = connection.prepareStatement("DELETE FROM program_rolle WHERE rolle_id = ?");
            insertStatement.setInt(1, rolle.getRolleID());
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement insertStatement = connection.prepareStatement("DELETE FROM roller WHERE rolle_id = ?");
            insertStatement.setInt(1, rolle.getRolleID());
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rollerIProgram.remove(rolle);
        rolle.fjernPersonFraRolle(rolle.getSpillesAf());
    }

    public String udskrivRollerIProgram() {
        StringBuilder returner = new StringBuilder();
        for(Rolle roller : rollerIProgram){
            returner.append(roller);
            returner.append("\n");
        }
        return returner.toString();
    }

    //Udskriver krediteringen sorteret i forhold til static-listen rolleTyper i Rolle-klassen.
    //Der skal fikses så den ikke skriver typer ud, som ikke er i programmet.
    public String udskrivKreditering(Producent producent){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Programmet er lavet af ").append(producent).append("\n\n");
        for(String type : Rolle.getRolleTyper()){
            stringBuilder.append(type).append("\n");
            for(Rolle rolle : rollerIProgram){
                if(rolle.getType().equals(type)){
                    stringBuilder.append(rolle).append("\n");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return titel;
    }

    //Gettere og settere

    public int getProgramID() {
        return programID;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        try{
            PreparedStatement insertStatement = connection.prepareStatement("UPDATE programmer SET navn = ? WHERE id = ?");
            insertStatement.setString(1, titel);
            insertStatement.setInt(2, this.programID);
            insertStatement.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.titel = titel;
    }

    public List<Rolle> getRollerIProgram() {
        return rollerIProgram;
    }

    public void setRollerIProgram(List<Rolle> rollerIProgram) {
        this.rollerIProgram = rollerIProgram;
    }


}
