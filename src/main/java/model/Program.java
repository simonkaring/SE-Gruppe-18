package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Program {

    private final UUID programID;
    private String titel;
    private List<Person> medvirkende;

    public Program(String titel) {
        this.titel = titel;
        this.programID = UUID.randomUUID();
        this.medvirkende = new ArrayList<>();
    }

    //Tilknytter person til programmet. Er personen allerede tilknyttet programmet, stopper metoden.
    public void addMedvirkende(Person person){
        for(int i = 0 ; i < getMedvirkende().size() ; i++){
            if(getMedvirkende().get(i).getPersonID() == person.getPersonID()){
                return;
            }
        }
        medvirkende.add(person);
    }

    //Fjerner person fra program.
    public void fjernMedvirkende(Person person){
        for(int i = 0 ; i < getMedvirkende().size() ; i++){
            if(getMedvirkende().get(i).getPersonID() == person.getPersonID()){
                medvirkende.remove(i);
                return;
            }
        }
    }

    //Udskriver den medvirkendes navn og en liste over hvilke roller personen har i programmet.
    //Kan evt udvides så krediteringerne udskrives i korrekt rækkefølge i forhold til rolle type.
    public void udskrivKreditering(){
        for(Person personIProgram : medvirkende){
            System.out.println(personIProgram + " som: " + personIProgram.printRoller(this));
        }
    }

    @Override
    public String toString(){
        return getTitel() + " " + getProgramID() + "\n" + getMedvirkende() + "\n";
    }

    public UUID getProgramID() {
        return programID;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public List<Person> getMedvirkende() {
        return medvirkende;
    }

    public void setMedvirkende(List<Person> medvirkende) {
        this.medvirkende = medvirkende;
    }

}
