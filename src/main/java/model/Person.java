package model;

import data.ConnectionDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Person extends ConnectionDatabase {

    private int personID;
    private String fornavn;
    private String efternavn;
    private String nationalitet;
    private LocalDate alder;
    private List<Rolle> roller;
    private String rolle;

    public Person(String fornavn, String efternavn, LocalDate alder, String nationalitet) {
        indsaetPerson(fornavn, efternavn, nationalitet, Integer.parseInt(alder.format(DateTimeFormatter.ofPattern("dd"))), Integer.parseInt(alder.format(DateTimeFormatter.ofPattern("MM"))), Integer.parseInt(alder.format(DateTimeFormatter.ofPattern("yyyy"))));
        try{
            int id = 0;
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM personer ORDER BY id DESC LIMIT 1");
            ResultSet queryResultSet = queryStatement.executeQuery();
            while(queryResultSet.next()){
                id = queryResultSet.getInt("id");
            }
            this.personID = id;
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.alder = alder;
        this.nationalitet = nationalitet;
        this.roller = new ArrayList<>();
        KrediteringSystem.getSamletPersoner().add(this);
    }

    public void tilknytTilRolle(Rolle rolle){
        rolle.tilknytPersonTilRolle(this);
    }

    public void fjernRolle(Rolle rolle){
        rolle.fjernPersonFraRolle(this);
    }

    @Override
    public String toString(){
        return fornavn + " " + efternavn;
    }

    //Gettere og settere

    public int getPersonID() {
        return personID;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getNationalitet() {
        return nationalitet;
    }

    public void setNationalitet(String nationalitet) {
        this.nationalitet = nationalitet;
    }

    public int getAlder() {
        return Period.between(alder, LocalDate.now()).getYears();
    }

    public LocalDate getAlderDate() {
        return alder;
    }

    public String getAlderDato() {
        return alder.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void setAlder(LocalDate alder) {
        this.alder = alder;
    }

    public List<Rolle> getRoller() {
        return roller;
    }

    public void setRoller(List<Rolle> roller) {
        this.roller = roller;
    }
    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }
}
