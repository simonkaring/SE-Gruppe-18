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

    public void addMedvirkende(Person person){
        for(int i = 0 ; i < getMedvirkende().size() ; i++){
            if(getMedvirkende().get(i).getPersonID() == person.getPersonID()){
                return;
            }
        }
        medvirkende.add(person);
    }

    public void fjernMedvirkende(Person person){
        for(int i = 0 ; i < getMedvirkende().size() ; i++){
            if(getMedvirkende().get(i).getPersonID() == person.getPersonID()){
                medvirkende.remove(i);
                return;
            }
        }
    }

    public void udskrivKreditering(){
        for(int i = 0 ; i < medvirkende.size() ; i++){

            System.out.println(getMedvirkende().get(i) + " som: " + getMedvirkende().get(i).printRoller(this));
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
