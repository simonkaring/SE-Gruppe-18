package model;

import data.ConnectionDatabase;
import view.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KrediteringSystem extends ConnectionDatabase {

    private static List<Producent> samletProducenter = new ArrayList<>();
    private static List<Program> samletProgrammer = new ArrayList<>();
    private static List<Rolle> samletRoller = new ArrayList<>();
    private static List<Person> samletPersoner = new ArrayList<>();

    public static void main(String[] args) {

        opstart();
        Main.main(args);
    }

    //Køres ved opstart af programmet, og henter al data fra databasen og opretter objekter af det.
    public static void opstart(){
        opretForbindelse();
        int counter = 0;
        //Indsætter producenter fra database til programmet.
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM producenter ORDER BY id");
            ResultSet queryResultSet = queryStatement.executeQuery();
            if(!queryResultSet.isBeforeFirst()){
                counter++;
            }
            while(queryResultSet.next()){
                new Producent(queryResultSet.getString("navn"), queryResultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Indsætter programmer fra database til programmet.
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM programmer ORDER BY id");
            ResultSet queryResultSet = queryStatement.executeQuery();
            if(!queryResultSet.isBeforeFirst()){
                counter++;
            }
            while(queryResultSet.next()){
                Program programmet = new Program(queryResultSet.getString("navn"), queryResultSet.getInt("id"));
                for(Producent producent : samletProducenter){
                    if(producent.getProducentID() == queryResultSet.getInt("producent_id")){
                        producent.getProgrammer().add(programmet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Indsætter personer fra database til programmet.
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM personer ORDER BY id");
            ResultSet queryResultSet = queryStatement.executeQuery();
            if(!queryResultSet.isBeforeFirst()){
                counter++;
            }
            while(queryResultSet.next()){
                new Person(queryResultSet.getString("fornavn"),
                        queryResultSet.getString("efternavn"),
                        LocalDate.of(queryResultSet.getInt("aar"), queryResultSet.getInt("maaned"), queryResultSet.getInt("dag")),
                        queryResultSet.getString("nationalitet"), queryResultSet.getInt("id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Indsætter roller fra database til programmet.
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM roller ORDER BY id");
            ResultSet queryResultSet = queryStatement.executeQuery();
            if(!queryResultSet.isBeforeFirst()){
                counter++;
            }
            while(queryResultSet.next()){
                if(queryResultSet.getInt("person_id") > 0){
                    Person denValgtePerson = null;
                    for(Person person : samletPersoner){
                        if(person.getPersonID() == queryResultSet.getInt("person_id")){
                            denValgtePerson = person;
                        }
                    }
                    Rolle rolle = new Rolle(queryResultSet.getString("navn"), queryResultSet.getString("type"), denValgtePerson, queryResultSet.getInt("id"));
                    denValgtePerson.getRoller().add(rolle);
                } else {
                    new Rolle(queryResultSet.getString("navn"), queryResultSet.getString("type"), null, queryResultSet.getInt("id"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM program_rolle");
            ResultSet queryResultSet = queryStatement.executeQuery();
            while(queryResultSet.next()){
                for(Program program : samletProgrammer){
                    if(program.getProgramID() == queryResultSet.getInt("program_id")){
                        for(Rolle rolle : samletRoller){
                            if(rolle.getRolleID() == queryResultSet.getInt("rolle_id")){
                                program.getRollerIProgram().add(rolle);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Hvis databasen er tom, oprettes der data.
        if(counter == 4){
            opretData();
        }
    }

    public static void opretProducent(String navn){
        new Producent(navn);
    }

    public static void opretProgram(String navn, Producent producent){
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

    //Hvis databasen er tom, opret data.
    public static void opretData(){
        Producent producent1 = new Producent("Den vilde producent");
        Producent producent2 = new Producent("ProdDK");
        Producent producent3 = new Producent("Danmarks helt egen producent");
        producent1.opretProgram("Drengerøvene");
        producent1.opretProgram("Er sparegrisen tom?");
        producent2.opretProgram("Den forsvundene skat");
        producent2.opretProgram("Vild med lakridser");
        producent3.opretProgram("Den døve bager");
        producent3.opretProgram("Tordenskrald");
        Person person1 = new Person("Hans", "Hansen", LocalDate.of(1965, 11, 30), "Danmark");
        Person person2 = new Person("Peter", "Petersen", LocalDate.of(1999, 1, 14), "Danmark");
        Person person3 = new Person("Lonnie", "Lonniesen", LocalDate.of(2010, 4, 18), "Danmark");
        Person person4 = new Person("Søren", "Sørensen", LocalDate.of(1987, 8, 1), "Tyskland");
        Person person5 = new Person("Gitte", "Gittesen", LocalDate.of(1998, 8, 29), "Danmark");
        Person person6 = new Person("Sofie", "Sofiesen", LocalDate.of(1988, 2, 3), "Danmark");
        Person person7 = new Person("Thomas", "Thomassen", LocalDate.of(1998, 6, 12), "Sverige");
        samletProgrammer.get(0).addRolle("Producer", "Producer", person1);
        samletProgrammer.get(0).addRolle("Thomas", "Skuespiller", person2);
        samletProgrammer.get(0).addRolle("Signe", "Skuespiller", person3);
        samletProgrammer.get(0).addRolle("Karsten", "Skuespiller", person4);
        samletProgrammer.get(0).addRolle("Karstens onde tvilling", "Skuespiller", person4);
        samletProgrammer.get(0).addRolle("Musik", "Lydteknikker", person5);
        samletProgrammer.get(0).addRolle("Lydeffekter", "Lydteknikker", person6);
        samletProgrammer.get(0).addRolle("Statist", "Statist", person7);
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
