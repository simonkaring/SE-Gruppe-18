package model;

import data.QueryDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static data.ConnectionDatabase.connection;

public class Production {

    private int programID;
    private String titel;
    private final List<Role> rollerIProgram;

    public Production(String titel, Producer producer) {
        QueryDatabase.insertProduction(titel, producer);
        try {
            int id = 0;
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM programmer ORDER BY id DESC LIMIT 1");
            ResultSet queryResultSet = queryStatement.executeQuery();
            while (queryResultSet.next()) {
                id = queryResultSet.getInt("id");
            }
            this.programID = id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.titel = titel;
        this.rollerIProgram = new ArrayList<>();
        KrediteringSystem.getSamletProgrammer().add(this);
    }

    public Production(String titel, int programID) {
        this.programID = programID;
        this.titel = titel;
        this.rollerIProgram = new ArrayList<>();
        KrediteringSystem.getSamletProgrammer().add(this);
    }


    // Laver rolle i programmet i rollerIProgram-listen, uden at tilknytte person til rollen.
    public void addRolle(String navn, String type) {
        for (Role roleIProgram : getRollerIProgram()) {
            if (roleIProgram.getNavn().equals(navn) && roleIProgram.getType().equals(type)) {
                return;
            }
        }
        Role role = new Role(navn, type);
        rollerIProgram.add(role);
        Role.addRolleType(role);
        try {
            PreparedStatement insertStatement = connection.prepareStatement("Insert INTO program_rolle (program_id, rolle_id) VALUES (?,?)");
            insertStatement.setInt(1, this.programID);
            insertStatement.setInt(2, role.getRolleID());
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Laver rolle i programmet i rollerIProgram-listen, og tilknytter person til rollen.
    public void addRolle(String navn, String type, Person person) {
        for (Role roleIProgram : getRollerIProgram()) {
            if (roleIProgram.getNavn().equals(navn) && roleIProgram.getType().equals(type)) {
                return;
            }
        }
        Role role = new Role(navn, type, person);
        rollerIProgram.add(role);
        Role.addRolleType(role);
        person.getRoller().add(role);
        try {
            PreparedStatement insertStatement = connection.prepareStatement("Insert INTO program_rolle (program_id, rolle_id) VALUES (?,?)");
            insertStatement.setInt(1, this.programID);
            insertStatement.setInt(2, role.getRolleID());
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fjerner den valgte rolle fra programmet.
    public void fjernRolle(Role role) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("DELETE FROM program_rolle WHERE rolle_id = ?");
            insertStatement.setInt(1, role.getRolleID());
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rollerIProgram.remove(role);
        role.fjernPersonFraRolle(role.getSpillesAf());
    }

    public String udskrivRollerIProgram() {
        StringBuilder returner = new StringBuilder();
        for (Role roller : rollerIProgram) {
            returner.append(roller);
            returner.append("\n");
        }
        return returner.toString();
    }

    // Udskriver krediteringen sorteret i forhold til static-listen rolleTyper i Rolle-klassen.
    // Der skal fikses så den ikke skriver typer ud, som ikke er i programmet.
    public String udskrivKreditering(Producer producer) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Programmet er lavet af ").append(producer).append("\n\n");
        for (String type : Role.getRolleTyper()) {
            stringBuilder.append(type).append("\n");
            for (Role role : rollerIProgram) {
                if (role.getType().equals(type)) {
                    stringBuilder.append(role).append("\n");
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

    // Gettere og settere

    public int getProgramID() {
        return programID;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("UPDATE programmer SET navn = ? WHERE id = ?");
            insertStatement.setString(1, titel);
            insertStatement.setInt(2, this.programID);
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.titel = titel;
    }

    public List<Role> getRollerIProgram() {
        return rollerIProgram;
    }

}
