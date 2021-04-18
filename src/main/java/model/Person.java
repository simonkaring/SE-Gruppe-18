package model;

import java.util.Date;

public class Person {

    private int personID;
    private String fornavn;
    private String efternavn;
    private Date alder;
    private String nationalitet;

    public Person(int personID, String fornavn, String efternavn, Date alder, String nationalitet) {
        this.personID = personID;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.alder = alder;
        this.nationalitet = nationalitet;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
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

    public Date getAlder() {
        return alder;
    }

    public void setAlder(Date alder) {
        this.alder = alder;
    }

    public String getNationalitet() {
        return nationalitet;
    }

    public void setNationalitet(String nationalitet) {
        this.nationalitet = nationalitet;
    }
}
