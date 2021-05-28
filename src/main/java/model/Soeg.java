package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class Soeg {

    private static List<List<Object>> soegeResultater;

    //Søgemetoden der søger på program, person og producent.
    public static void soege(String soegeTekst){

        soegeResultater = new ArrayList<>();

        soegProgram(soegeTekst);
        soegPerson(soegeTekst);
        soegProducent(soegeTekst);

        int tomSoegning = 0;

        for (List<Object> objects : soegeResultater) {
            if (objects.isEmpty()) {
                tomSoegning += 1;
            }
        }

        if(tomSoegning == 0){
            System.out.println("Der blev ikke fundet nogle resultater");
        }

    }

    //Søger i den statiske list samletProgrammer under KrediteringSystem.
    public static void soegProgram(String soegeTekst){
        soegeResultater.add(new ArrayList<>());
        for(Program programmer : KrediteringSystem.getSamletProgrammer()){
            if(programmer.getTitel().toLowerCase(Locale.ROOT).contains(soegeTekst.toLowerCase(Locale.ROOT))){
                getSoegeResultater().get(0).add(programmer);
            }
        }
    }

    //Søger i den statiske list samletPersoner under KrediteringSystem.
    public static void soegPerson(String soegeTekst){
        soegeResultater.add(new ArrayList<>());
        for(Person personer : KrediteringSystem.getSamletPersoner()){
            if(personer.getFornavn().contains(soegeTekst) || personer.getEfternavn().contains(soegeTekst)){
                getSoegeResultater().get(1).add(personer);
            }

        }
    }

    //Søger i den statiske list samletProducenter under KrediteringSystem.
    public static void soegProducent(String soegeTekst){
        soegeResultater.add(new ArrayList<>());
        for(Producent producenter : KrediteringSystem.getSamletProducenter()){
            if(producenter.getNavn().contains(soegeTekst)){
                getSoegeResultater().get(2).add(producenter);
            }
        }
    }

    //Søger på alle personer, programmer og producenter, og returner objektet med samme id.
    public static Object soegPaaID(UUID id){
        Object returner = null;
        for(Person person : KrediteringSystem.getSamletPersoner()){
            if(person.getPersonID().equals(id)){
                returner = person;
            }
        }
        for(Program program : KrediteringSystem.getSamletProgrammer()){
            if(program.getProgramID().equals(id)){
                returner = program;
            }
        }
        for(Producent producent : KrediteringSystem.getSamletProducenter()){
            if(producent.getProducentID().equals(id)){
                returner = producent;
            }
        }
        if(returner == null){
            returner = "Der blev ikke fundet noget søgeresultat.";
        }
        return returner;
    }

    public static List<List<Object>> getSoegeResultater() {
        return soegeResultater;
    }
}
