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
        Rolle rolle = new Rolle(navn, type);
        rollerIProgram.add(rolle);
        Rolle.addRolleType(rolle);
    }

    //Laver rolle i programmet i rollerIProgram-listen, og tilknytter person til rollen.
    public void addRolle(String navn, String type, Person person){
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
    public void udskrivKreditering(Producent producent){
        System.out.println("Programmet er lavet af " + producent + "\n");
        for(String type : Rolle.getRolleTyper()){
            System.out.println(type + ":");
            for(Rolle rolle : rollerIProgram){
                if(rolle.getType().equals(type)){
                    System.out.println(rolle);
                }
            }
            System.out.println("");
        }
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
