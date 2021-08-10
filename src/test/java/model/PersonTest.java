package model;

import data.ConnectionDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import model.Production;
import view.EditorController;

import static org.junit.jupiter.api.Assertions.*;
import static data.ConnectionDatabase.connection;

class PersonTest {

    private Person person;
    Production currentProduction;

    @BeforeEach
    void setUp() {
        ConnectionDatabase.opretForbindelse();
        currentProduction = EditorController.findProduction();
        LocalDate date = LocalDate.of(2000,01,01);
        person = new Person("Jens", "Jensen", date, "Dansk");
    }

    @Test
    void tilknytTilRolle() {
    }

    @Test
    void fjernRolle() {
    }

    @Test
    void getFornavn() {
    }

    @Test
    void setFornavn() {
        person.setFornavn("Poppy");
        String expectedString = "Poppy";
        assertEquals(expectedString,person.getFornavn());
    }

    @AfterEach
    void tearDown() {

//        currentProduction.fjernRolle(role);
    }
}