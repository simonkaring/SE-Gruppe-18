package model;

import java.util.UUID;

public class Rolle{

    private final UUID programID;
    private String navn;
    private String type;

    public Rolle(UUID programID, String navn, String type) {
        this.navn = navn;
        this.type = type;
        this.programID = programID;
    }

    @Override
    public String toString(){
        return navn;
    }

    public UUID getProgramID() {
        return programID;
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
