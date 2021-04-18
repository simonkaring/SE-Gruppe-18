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
    private LocalDate alder;
    private String nationalitet;
    private List<Rolle> roller;

    public Person(String fornavn, String efternavn, LocalDate alder, String nationalitet) {
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.alder = alder;
        this.nationalitet = nationalitet;
        this.personID = UUID.randomUUID();
        this.roller = new ArrayList<>();
    }

    //Tilføjer rolle som er tilknyttet til person og program.
    public void addRolle(Program program, String navn, String type){
        this.roller.add(new Rolle(program.getProgramID(), navn, type));
    }

    @Override
    public String toString(){
        return getFornavn() + " " + getEfternavn();
    }

    public String toFullString(){
        return getFornavn() + " " + getEfternavn() + " " + getAlder() + " " + getNationalitet() + " " + getPersonID() + "\n" + getRoller() + "\n";
    }

    //Udskriver personens roller som har samme ID som programmet.
    public List<Rolle> printRoller(Program program){
        ArrayList<Rolle> temp = new ArrayList<>();
        for(Rolle rolle : getRoller()){
            if(rolle.getProgramID().equals(program.getProgramID())){
                temp.add(rolle);
            }
        }
        return temp;
    }

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

    public int getAlder() {
        return Period.between(alder, LocalDate.now()).getYears();
    }

    public String getAlderDato() {
        return alder.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void setAlder(LocalDate alder) {
        this.alder = alder;
    }

    public String getNationalitet() {
        return nationalitet;
    }

    public void setNationalitet(String nationalitet) {
        this.nationalitet = nationalitet;
    }

    public List<Rolle> getRoller() {
        return roller;
    }

    public void setRoller(List<Rolle> roller) {
        this.roller = roller;
    }
}
