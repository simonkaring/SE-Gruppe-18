package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Producent {

    private final UUID producentID;
    private String navn;
    private List<Program> programmer;

    public Producent(String navn){
        this.producentID = UUID.randomUUID();
        this.navn = navn;
        this.programmer = new ArrayList<>();
        KrediteringSystem.getSamletProducenter().add(this);
    }

    //Opretter et program, som bliver sat en på "programmer"-listen.
    public void opretProgram(String navn){
        Program nytProgram = new Program(navn);
        programmer.add(nytProgram);
    }

    @Override
    public String toString() {
        return navn;
    }

    //Gettere og settere

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
