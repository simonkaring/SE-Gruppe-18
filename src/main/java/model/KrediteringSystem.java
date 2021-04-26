package model;

import view.Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KrediteringSystem {

    private static List<Producent> samletProducenter = new ArrayList<>();
    private static List<Program> samletProgrammer = new ArrayList<>();
    private static List<Rolle> samletRoller = new ArrayList<>();
    private static List<Person> samletPersoner = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Nuværende rolle typer: " + Rolle.getRolleTyper());

        Producent producent = new Producent("Top Dogs");
        System.out.println(producent);
        System.out.println(producent.getProgrammer());
        producent.opretProgram("Bjørnehunden Silver");
        System.out.println(producent.getProgrammer());
        producent.opretProgram("Rambo");
        producent.opretProgram("Terminator");
        System.out.println(producent.getProgrammer());
        Program program1 = producent.getProgrammer().get(0);
        program1.addRolle("Overproducer", "Producer");
        program1.addRolle("Torben", "Skuespiller");
        program1.addRolle("Lise", "Skuespiller");
        program1.addRolle("Tim", "Skuespiller");
        program1.addRolle("Henriette", "Skuespiller");
        program1.addRolle("Lydeffekter", "Lyd");
        program1.addRolle("Tekstforfatter", "Tekstforfatter");
        System.out.println(program1.udskrivRollerIProgram());
        program1.getRollerIProgram().get(0).tilknytPersonTilRolle(new Person("Hans", "Hansen", LocalDate.of(1965, 11, 30), "Danmark"));
        program1.getRollerIProgram().get(1).tilknytPersonTilRolle(new Person("Peter", "Petersen", LocalDate.of(1999, 1, 14), "Danmark"));
        program1.getRollerIProgram().get(2).tilknytPersonTilRolle(new Person("Lonnie", "Lonniesen", LocalDate.of(2010, 4, 18), "Danmark"));
        program1.getRollerIProgram().get(3).tilknytPersonTilRolle(new Person("Søren", "Sørensen", LocalDate.of(1987, 8, 1), "Tyskland"));
        program1.getRollerIProgram().get(4).tilknytPersonTilRolle(new Person("Gitte", "Gittesen", LocalDate.of(1998, 8, 29), "Danmark"));
        program1.getRollerIProgram().get(5).tilknytPersonTilRolle(new Person("Sofie", "Sofiesen", LocalDate.of(1988, 2, 3), "Danmark"));
        program1.getRollerIProgram().get(6).tilknytPersonTilRolle(new Person("Thomas", "Thomassen", LocalDate.of(1998, 6, 12), "Sverige"));
        System.out.println(program1.udskrivRollerIProgram());
        System.out.println("........-------------''''''''");
        program1.udskrivKreditering(producent);
        System.out.println("Nuværende rolle typer: " + Rolle.getRolleTyper());

        program1.addRolle("Klipper", "Klipper");

        Person nyPerson = new Person("Kasper", "Sørensen", LocalDate.of(1998, 8, 29), "Danmark");
        nyPerson.tilknytTilRolle(program1.getRollerIProgram().get(7));
        Rolle testRolle = new Rolle("Klipper", "Klipper");
        Rolle.addRolleType(testRolle);
        System.out.println("Nuværende rolle typer: " + Rolle.getRolleTyper());
        System.out.println("........-------------''''''''");
        program1.udskrivKreditering(producent);
        System.out.println("........-------------''''''''");

        System.out.println(getSamletProducenter());
        opretProducent("TestProducent");
        System.out.println(getSamletProducenter());
        System.out.println("........-------------''''''''");
        System.out.println(getSamletProgrammer());
        opretprogram(getSamletProducenter().get(0),"programTest");
        System.out.println(getSamletProgrammer());
        System.out.println("........-------------''''''''");
        System.out.println(getSamletProducenter());
        System.out.println(getSamletProgrammer());
        System.out.println(getSamletRoller());
        System.out.println(getSamletPersoner());
        System.out.println("........-------------''''''''");
        udskrivKreditering(producent, program1);
        System.out.println("........-------------''''''''");
        System.out.println("........-------------''''''''");
        System.out.println("........-------------''''''''");
        System.out.println(testRolle);
        System.out.println(nyPerson);
        System.out.println(program1.getRollerIProgram());
        Main.main(args);
    }

    public static void opretProducent(String navn){
        new Producent(navn);
    }

    public static void opretprogram(Producent producent, String navn){
        for(Producent producenten : getSamletProducenter()){
            if(producenten.equals(producent)){
                new Program(navn);
                return;
            }
        }
        System.out.println("Programmet kunne ikke oprettes.");
    }

    //Laver rolle i programmet i rollerIProgram-listen, uden at tilknytte person til rollen.
    public static void addRolle(Program program, String navn, String type){
        Rolle rolle = new Rolle(navn, type);
        program.getRollerIProgram().add(rolle);
        Rolle.addRolleType(rolle);
    }

    //Laver rolle i programmet i rollerIProgram-listen, og tilknytter person til rollen.
    public static void addRolle(Program program, String navn, String type, Person person){
        Rolle rolle = new Rolle(navn, type, person);
        program.getRollerIProgram().add(rolle);
        Rolle.addRolleType(rolle);
    }

    //Fjerner den valgte rolle fra programmet.
    public static void fjernRolle(Program program, Rolle rolle){
        program.getRollerIProgram().remove(rolle);
        rolle.fjernPersonFraRolle(rolle.getSpillesAf());
        getSamletRoller().remove(rolle);
    }

    public static String udskrivRollerIProgram(Program program) {
        StringBuilder returner = new StringBuilder();
        for(Rolle roller : program.getRollerIProgram()){
            returner.append(roller);
            returner.append("\n");
        }
        return returner.toString();
    }

    //Udskriver krediteringen sorteret i forhold til static-listen rolleTyper i Rolle-klassen.
    //Der skal fikses så den ikke skriver typer ud, som ikke er i programmet.
    public static void udskrivKreditering(Producent producent, Program program){
        System.out.println("Programmet er lavet af " + producent + "\n");
        for(String type : Rolle.getRolleTyper()){
            System.out.println(type + ":");
            for(Rolle rolle : program.getRollerIProgram()){
                if(rolle.getType().equals(type)){
                    System.out.println(rolle);
                }
            }
            System.out.println("");
        }
    }

    //Tilknyt person til rollen.
    public void tilknytPersonTilRolle(Rolle rolle, Person person){
        rolle.setSpillesAf(person);
    }

    //Fjern person fra rollen.
    public void fjernPersonFraRolle(Rolle rolle, Person person){
        if(rolle.getSpillesAf().equals(person)){
            rolle.setSpillesAf(null);
        }
    }

    //Gettere og settere

    public static List<Producent> getSamletProducenter() {
        return samletProducenter;
    }

    public static void setSamletProducenter(List<Producent> samletProducenter) {
        KrediteringSystem.samletProducenter = samletProducenter;
    }

    public static List<Program> getSamletProgrammer() {
        return samletProgrammer;
    }

    public static void setSamletProgrammer(List<Program> samletProgrammer) {
        KrediteringSystem.samletProgrammer = samletProgrammer;
    }

    public static List<Rolle> getSamletRoller() {
        return samletRoller;
    }

    public static void setSamletRoller(List<Rolle> samletRoller) {
        KrediteringSystem.samletRoller = samletRoller;
    }

    public static List<Person> getSamletPersoner() {
        return samletPersoner;
    }

    public static void setSamletPersoner(List<Person> samletPersoner) {
        KrediteringSystem.samletPersoner = samletPersoner;
    }
}
