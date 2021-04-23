package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Person {

    private final UUID personID;
    private String fornavn;
    private String efternavn;
    private String nationalitet;
    private LocalDate alder;
    private List<Rolle> roller;

    public Person(String fornavn, String efternavn, LocalDate alder, String nationalitet) {
        this.personID = UUID.randomUUID();
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

    public UUID getPersonID() {
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
}
