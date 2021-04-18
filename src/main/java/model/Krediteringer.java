package model;

public class Krediteringer {

    private int ID;
    private String navn;
    private String titel;

    public Krediteringer(int ID, String navn, String titel) {
        this.ID = ID;
        this.navn = navn;
        this.titel = titel;
    }

    public void addKreditering(int ID, String navn, String titel, Rolle rolle){
        
    }

    public void changeKreditering(int ID, String navn, String title, Rolle rolle){

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }
}
