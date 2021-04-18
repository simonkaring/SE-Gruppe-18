package model;

public class Rolle {

    private String navn;
    private String type;

    public Rolle(String navn, String type) {
        this.navn = navn;
        this.type = type;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
