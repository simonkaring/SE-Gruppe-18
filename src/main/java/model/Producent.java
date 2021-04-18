package model;

public class Producent {

    private String producentNavn;
    private int producentID;

    public Producent(String producentNavn, int producentID) {
        this.producentNavn = producentNavn;
        this.producentID = producentID;
    }

    public String getProducentNavn() {
        return producentNavn;
    }

    public void setProducentNavn(String producentNavn) {
        this.producentNavn = producentNavn;
    }

    public int getProducentID() {
        return producentID;
    }

    public void setProducentID(int producentID) {
        this.producentID = producentID;
    }
}
