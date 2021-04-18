package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Test {

    public static void main(String[] args) {

        Producent producent = new Producent("DanProducenten");
        System.out.println(producent.getProducentID());
        System.out.println(producent.getNavn());
        System.out.println(producent.getProgrammer());

        System.out.println("----------------------");

        producent.opretProgram("Master Chef");
        System.out.println(producent.getProgrammer());

        System.out.println("----------------------");

        producent.opretProgram("Bjørnehunden Silver");
        System.out.println(producent.getProgrammer());

        System.out.println("----------------------");

        Person person1 = new Person("Thomas", "Træsko", LocalDate.of(1978, 3, 8), "Danmark");
        System.out.println(person1);
        Person person2 = new Person("Louise", "Lurendrejer", LocalDate.of(2002, 8, 19), "Bangladesh");
        System.out.println(person2);

        System.out.println("----------------------");

        producent.getProgrammer().get(0).addMedvirkende(person1);
        producent.getProgrammer().get(1).addMedvirkende(person1);
        producent.getProgrammer().get(1).addMedvirkende(person2);
        System.out.println(producent.getProgrammer());

        System.out.println("----------------------");

        person1.addRolle(producent.getProgrammer().get(1), "Akakabuto","Skuespiller");
        person1.addRolle(producent.getProgrammer().get(1), "Silver","Skuespiller");
        person1.addRolle(producent.getProgrammer().get(0), "Dommer","Skuespiller");
        person2.addRolle(producent.getProgrammer().get(1), "Daisuke","Skuespiller");
        System.out.println(person1 + " som: " + person1.printRoller(producent.getProgrammer().get(1)));
        System.out.println(person1.printRoller(producent.getProgrammer().get(0)));

        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("----------------------");

        producent.getProgrammer().get(1).udskrivKreditering();

    }

}
