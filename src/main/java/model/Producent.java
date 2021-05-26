package model;

import data.ConnectionDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Producent extends ConnectionDatabase {

    private int producentID;
    private String navn;
    private List<Program> programmer;

    public Producent(String navn){
        indsaetProducent(navn);
        try{
            int id = 0;
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM producenter ORDER BY id DESC LIMIT 1");
            ResultSet queryResultSet = queryStatement.executeQuery();
            while(queryResultSet.next()){
                id = queryResultSet.getInt("id");
            }
            this.producentID = id;
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.navn = navn;
        this.programmer = new ArrayList<>();
        KrediteringSystem.getSamletProducenter().add(this);
    }

    //Opretter et program, som bliver sat en på "programmer"-listen.
    public void opretProgram(String navn, Producent p){
        Program nytProgram = new Program(navn, this);
        programmer.add(nytProgram);
    }

    @Override
    public String toString() {
        return navn;
    }

    //Gettere og settere

    public int getProducentID() {
        return producentID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public List<Program> getProgrammer() {
        return programmer;
    }

    public void setProgrammer(List<Program> programmer) {
        this.programmer = programmer;
    }
}
