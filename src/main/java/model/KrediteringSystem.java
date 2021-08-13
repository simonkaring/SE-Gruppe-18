package model;

import data.ConnectionDatabase;
import data.QueryDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KrediteringSystem extends ConnectionDatabase {

    private static List<Producer> samletProducenter = new ArrayList<>();
    private static List<Production> samletProgrammer = new ArrayList<>();
    private static List<Role> samletRoller = new ArrayList<>();
    private static List<Person> samletPersoner = new ArrayList<>();

    // Køres ved opstart af programmet, og henter al data fra databasen og opretter objekter af det.
    public static void opstart2() throws SQLException {
        opretForbindelse();
        int counter = 0;

        // Retrieve producers
        ResultSet selectProducerResultSet = QueryDatabase.selectProducer();
        if (!selectProducerResultSet.isBeforeFirst()) {
            counter++;
        }
        while (selectProducerResultSet.next()) {
            new Producer(selectProducerResultSet.getString("navn"), selectProducerResultSet.getInt("id"));
        }

        // Retrieve Productions
        ResultSet selectProductionResultSet = QueryDatabase.selectProduction();
        if (!selectProductionResultSet.isBeforeFirst()) {
            counter++;
        }
        while (selectProductionResultSet.next()) {
            Production programmet = new Production(selectProductionResultSet.getString("navn"), selectProductionResultSet.getInt("id"));
            for (Producer producer : samletProducenter) {
                if (producer.getProducentID() == selectProductionResultSet.getInt("producent_id")) {
                    producer.getProgrammer().add(programmet);
                }
            }
        }

        // Retrieve People
        ResultSet selectPersonResultSet = QueryDatabase.selectPerson();
        if (!selectPersonResultSet.isBeforeFirst()) {
            counter++;
        }
        while (selectPersonResultSet.next()) {
            new Person(selectPersonResultSet.getString("fornavn"),
                    selectPersonResultSet.getString("efternavn"),
                    LocalDate.of(selectPersonResultSet.getInt("aar"), selectPersonResultSet.getInt("maaned"), selectPersonResultSet.getInt("dag")),
                    selectPersonResultSet.getString("nationalitet"), selectPersonResultSet.getInt("id")
            );
        }

        // Retrieve Roles
        ResultSet queryResultSet = QueryDatabase.selectRole();
        if (!queryResultSet.isBeforeFirst()) {
            counter++;
        }
        while (queryResultSet.next()) {
            if (queryResultSet.getInt("person_id") > 0) {
                Person denValgtePerson = null;
                for (Person person : samletPersoner) {
                    if (person.getPersonID() == queryResultSet.getInt("person_id")) {
                        denValgtePerson = person;
                    }
                }
                Role role = new Role(queryResultSet.getString("navn"), queryResultSet.getString("type"), denValgtePerson, queryResultSet.getInt("id"));
                denValgtePerson.getRoller().add(role);
            } else {
                new Role(queryResultSet.getString("navn"), queryResultSet.getString("type"), null, queryResultSet.getInt("id"));
            }
        }

        // Retrieve Production Roles

    }

    public static void opstart() {
        opretForbindelse();
        int counter = 0;
        // Indsætter producenter fra database til programmet.
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM producenter ORDER BY id");
            ResultSet queryResultSet = queryStatement.executeQuery();
            if (!queryResultSet.isBeforeFirst()) {
                counter++;
            }
            while (queryResultSet.next()) {
                new Producer(queryResultSet.getString("navn"), queryResultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Indsætter programmer fra database til programmet.
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM programmer ORDER BY id");
            ResultSet queryResultSet = queryStatement.executeQuery();
            if (!queryResultSet.isBeforeFirst()) {
                counter++;
            }
            while (queryResultSet.next()) {
                Production programmet = new Production(queryResultSet.getString("navn"), queryResultSet.getInt("id"));
                for (Producer producer : samletProducenter) {
                    if (producer.getProducentID() == queryResultSet.getInt("producent_id")) {
                        producer.getProgrammer().add(programmet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Indsætter personer fra database til programmet.
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM personer ORDER BY id");
            ResultSet queryResultSet = queryStatement.executeQuery();
            if (!queryResultSet.isBeforeFirst()) {
                counter++;
            }
            while (queryResultSet.next()) {
                new Person(queryResultSet.getString("fornavn"),
                        queryResultSet.getString("efternavn"),
                        LocalDate.of(queryResultSet.getInt("aar"), queryResultSet.getInt("maaned"), queryResultSet.getInt("dag")),
                        queryResultSet.getString("nationalitet"), queryResultSet.getInt("id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Indsætter roller fra database til programmet.
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM roller ORDER BY id");
            ResultSet queryResultSet = queryStatement.executeQuery();
            if (!queryResultSet.isBeforeFirst()) {
                counter++;
            }
            while (queryResultSet.next()) {
                if (queryResultSet.getInt("person_id") > 0) {
                    Person denValgtePerson = null;
                    for (Person person : samletPersoner) {
                        if (person.getPersonID() == queryResultSet.getInt("person_id")) {
                            denValgtePerson = person;
                        }
                    }
                    Role role = new Role(queryResultSet.getString("navn"), queryResultSet.getString("type"), denValgtePerson, queryResultSet.getInt("id"));
                    denValgtePerson.getRoller().add(role);
                } else {
                    new Role(queryResultSet.getString("navn"), queryResultSet.getString("type"), null, queryResultSet.getInt("id"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Henter data fra program_rolle tabellen.
        try {
            PreparedStatement queryStatement = connection.prepareStatement("SELECT * FROM program_rolle");
            ResultSet queryResultSet = queryStatement.executeQuery();
            while (queryResultSet.next()) {
                for (Production production : samletProgrammer) {
                    if (production.getProgramID() == queryResultSet.getInt("program_id")) {
                        for (Role role : samletRoller) {
                            if (role.getRolleID() == queryResultSet.getInt("rolle_id")) {
                                production.getRollerIProgram().add(role);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Hvis databasen er tom, oprettes der data.
        if (counter == 4) {
            opretData();
        }
    }

    public static void opretProducent(String navn) {
        new Producer(navn);
    }

    public static void opretProgram(Producer producer, String navn) {
        producer.opretProgram(navn);
    }

    // Laver rolle i programmet i rollerIProgram-listen, uden at tilknytte person til rollen.
    public static void addRolle(Production production, String navn, String type) {
        production.addRolle(navn, type);
    }

    // Laver rolle i programmet i rollerIProgram-listen, og tilknytter person til rollen.
    public static void addRolle(Production production, String navn, String type, Person person) {
        production.addRolle(navn, type, person);
    }

    // Fjerner den valgte rolle fra programmet.
    public static void fjernRolle(Production production, Role role) {
        production.fjernRolle(role);
    }

    public static String udskrivRollerIProgram(Production production) {
        return production.udskrivRollerIProgram();
    }

    // Udskriver krediteringen sorteret i forhold til static-listen rolleTyper i Rolle-klassen.
    // Der skal fikses så den ikke skriver typer ud, som ikke er i programmet.
    public static String udskrivKreditering(Producer producer, Production production) {
        return production.udskrivKreditering(producer);
    }

    // Tilknyt person til rollen.
    public static void tilknytPersonTilRolle(Role role, Person person) {
        role.setSpillesAf(person);
    }

    // Fjern person fra rollen.
    public static void fjernPersonFraRolle(Role role, Person person) {
        if (role.getSpillesAf().equals(person)) {
            role.setSpillesAf(null);
        }
    }

    // Hvis databasen er tom, opret data.
    public static void opretData() {
        Producer producer1 = new Producer("Den vilde producent");
        Producer producer2 = new Producer("ProdDK");
        Producer producer3 = new Producer("Danmarks helt egen producent");
        producer1.opretProgram("Drengerøvene");
        producer1.opretProgram("Er sparegrisen tom?");
        producer2.opretProgram("Den forsvundne skat");
        producer2.opretProgram("Vild med lakridser");
        producer3.opretProgram("Den døve bager");
        producer3.opretProgram("Tordenskrald");
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
        samletProgrammer.get(4).addRolle("Bagermester Jensen", "Skuespiller", person7);

    }

    // Gettere og settere

    public static List<Producer> getSamletProducenter() {
        return samletProducenter;
    }

    public static void setSamletProducenter(List<Producer> samletProducenter) {
        KrediteringSystem.samletProducenter = samletProducenter;
    }

    public static List<Production> getSamletProgrammer() {
        return samletProgrammer;
    }

    public static void setSamletProgrammer(List<Production> samletProgrammer) {
        KrediteringSystem.samletProgrammer = samletProgrammer;
    }

    public static List<Role> getSamletRoller() {
        return samletRoller;
    }

    public static void setSamletRoller(List<Role> samletRoller) {
        KrediteringSystem.samletRoller = samletRoller;
    }

    public static List<Person> getSamletPersoner() {
        return samletPersoner;
    }

    public static void setSamletPersoner(List<Person> samletPersoner) {
        KrediteringSystem.samletPersoner = samletPersoner;
    }
}
