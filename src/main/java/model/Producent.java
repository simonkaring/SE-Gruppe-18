package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Producent {

    private final UUID producentID;
    private String navn;
    private List<Program> programmer;

    public Producent(String navn) {
        this.navn = navn;
        this.producentID = UUID.randomUUID();
        this.programmer = new ArrayList<>();
    }

    //Opretter et program, som bliver sat en på "programmer"-listen.
    public void opretProgram(String navn){
        programmer.add(new Program(navn));
    }

    public UUID getProducentID() {
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
