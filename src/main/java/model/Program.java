package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Program {

    private final UUID programID;
    private String titel;
    private List<Rolle> rollerIProgram;

    public Program(String titel){
        this.programID = UUID.randomUUID();
        this.titel = titel;
        this.rollerIProgram = new ArrayList<>();
        KrediteringSystem.getSamletProgrammer().add(this);
    }

    //Laver rolle i programmet i rollerIProgram-listen, uden at tilknytte person til rollen.
    public void addRolle(String navn, String type){
        for(Rolle rolleIProgram : getRollerIProgram()){
            if(rolleIProgram.getNavn().equals(navn) && rolleIProgram.getType().equals(type)){
                return;
            }
        }
        Rolle rolle = new Rolle(navn, type);
        rollerIProgram.add(rolle);
        Rolle.addRolleType(rolle);
    }

    //Laver rolle i programmet i rollerIProgram-listen, og tilknytter person til rollen.
    public void addRolle(String navn, String type, Person person){
        for(Rolle rolleIProgram : getRollerIProgram()){
            if(rolleIProgram.getNavn().equals(navn) && rolleIProgram.getType().equals(type)){
                return;
            }
        }
        Rolle rolle = new Rolle(navn, type, person);
        rollerIProgram.add(rolle);
        Rolle.addRolleType(rolle);
    }

    //Fjerner den valgte rolle fra programmet.
    public void fjernRolle(Rolle rolle){
        rollerIProgram.remove(rolle);
        rolle.fjernPersonFraRolle(rolle.getSpillesAf());
    }

    public String udskrivRollerIProgram() {
        StringBuilder returner = new StringBuilder();
        for(Rolle roller : rollerIProgram){
            returner.append(roller);
            returner.append("\n");
        }
        return returner.toString();
    }

    //Udskriver krediteringen sorteret i forhold til static-listen rolleTyper i Rolle-klassen.
    //Der skal fikses så den ikke skriver typer ud, som ikke er i programmet.
    public String udskrivKreditering(Producent producent){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Programmet er lavet af ").append(producent).append("\n\n");
        for(String type : Rolle.getRolleTyper()){
            stringBuilder.append(type).append("\n");
            for(Rolle rolle : rollerIProgram){
                if(rolle.getType().equals(type)){
                    stringBuilder.append(rolle).append("\n");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return titel;
    }

    //Gettere og settere

    public UUID getProgramID() {
        return programID;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public List<Rolle> getRollerIProgram() {
        return rollerIProgram;
    }

    public void setRollerIProgram(List<Rolle> rollerIProgram) {
        this.rollerIProgram = rollerIProgram;
    }


}
