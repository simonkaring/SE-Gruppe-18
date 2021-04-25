package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        program1.getRollerIProgram().get(6).tilknytPersonTilRolle(new Person("Peter", "Petersen", LocalDate.of(1999, 1, 14), "Danmark"));
        program1.getRollerIProgram().get(3).tilknytPersonTilRolle(new Person("Lonnie", "Lonniesen", LocalDate.of(2010, 4, 18), "Danmark"));
        program1.getRollerIProgram().get(2).tilknytPersonTilRolle(new Person("Søren", "Sørensen", LocalDate.of(1987, 8, 1), "Tyskland"));
        program1.getRollerIProgram().get(4).tilknytPersonTilRolle(new Person("Gitte", "Gittesen", LocalDate.of(1998, 8, 29), "Danmark"));
        program1.getRollerIProgram().get(5).tilknytPersonTilRolle(new Person("Sofie", "Sofiesen", LocalDate.of(1988, 2, 3), "Danmark"));
        program1.getRollerIProgram().get(1).tilknytPersonTilRolle(new Person("Thomas", "Thomassen", LocalDate.of(1998, 6, 12), "Sverige"));
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
        opretProgram(getSamletProducenter().get(0),"programTest");
        System.out.println(getSamletProgrammer());
        System.out.println("........-------------''''''''");
        System.out.println(getSamletProducenter());
        System.out.println(getSamletProgrammer());
        System.out.println(getSamletRoller());
        System.out.println(getSamletPersoner());
        System.out.println("........-------------''''''''");
        System.out.println(udskrivKreditering(producent, program1));
        System.out.println("........-------------''''''''");
        System.out.println("........-------------''''''''");
        System.out.println("........-------------''''''''");
        System.out.println(testRolle);
        System.out.println(nyPerson);
        System.out.println(program1.getRollerIProgram());
        System.out.println("........-------------''''''''");
        System.out.println("........-------------''''''''");
        System.out.println("........-------------''''''''");
        System.out.println("........-------------''''''''");
        System.out.println(program1.udskrivKreditering(producent));
        System.out.println(program1.getRollerIProgram());
        System.out.println("........-------------''''''''");
        System.out.println(udskrivKreditering(producent, program1));
        System.out.println(udskrivRollerIProgram(program1));
        if(program1.getTitel().contains("ør")){
            System.out.println("Det gør den!");
        }
        System.out.println("........-------------''''''''");
        System.out.println("........-------------''''''''");
        System.out.println("........-------------''''''''");
        Soeg.soege("r");
        System.out.println(Soeg.getSoegeResultater());
        Soeg.soege("e");
        System.out.println(Soeg.getSoegeResultater());
        System.out.println(Soeg.soegPaaID(program1.getProgramID()));
        System.out.println(Soeg.soegPaaID(UUID.randomUUID()));
    }

    public static void opretProducent(String navn){
        new Producent(navn);
    }

    public static void opretProgram(Producent producent, String navn){
        producent.opretProgram(navn);
    }

    //Laver rolle i programmet i rollerIProgram-listen, uden at tilknytte person til rollen.
    public static void addRolle(Program program, String navn, String type){
        program.addRolle(navn, type);
    }

    //Laver rolle i programmet i rollerIProgram-listen, og tilknytter person til rollen.
    public static void addRolle(Program program, String navn, String type, Person person){
        program.addRolle(navn, type, person);
    }

    //Fjerner den valgte rolle fra programmet.
    public static void fjernRolle(Program program, Rolle rolle){
        program.fjernRolle(rolle);
    }

    public static String udskrivRollerIProgram(Program program) {
        return program.udskrivRollerIProgram();
    }

    //Udskriver krediteringen sorteret i forhold til static-listen rolleTyper i Rolle-klassen.
    //Der skal fikses så den ikke skriver typer ud, som ikke er i programmet.
    public static String udskrivKreditering(Producent producent, Program program){
        return program.udskrivKreditering(producent);
    }

    //Tilknyt person til rollen.
    public static void tilknytPersonTilRolle(Rolle rolle, Person person){
        rolle.setSpillesAf(person);
    }

    //Fjern person fra rollen.
    public static void fjernPersonFraRolle(Rolle rolle, Person person){
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
