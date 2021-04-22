package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Rolle {

    private final UUID rolleID;
    private String navn;
    private String type;
    private Person spillesAf;

    //Afgør rækkefølgen krediteringen bliver udskrevet. Kan evt hardcodes.
    private static List<String> rolleTyper = new ArrayList<>();

    public Rolle(String navn, String type){
        this.rolleID = UUID.randomUUID();
        this.navn = navn;
        this.type = type;
        KrediteringSystem.getSamletRoller().add(this);
    }

    public Rolle(String navn, String type, Person spillesAF){
        this.rolleID = UUID.randomUUID();
        this.navn = navn;
        this.type = type;
        this.spillesAf = spillesAF;
        KrediteringSystem.getSamletRoller().add(this);
    }

    //Tilknyt person til rollen.
    public void tilknytPersonTilRolle(Person person){
        this.spillesAf = person;
    }

    //Fjern person fra rollen.
    public void fjernPersonFraRolle(Person person){
        if(this.spillesAf.equals(person)){
            this.spillesAf = null;
        }
    }

    //Hvis rollen ikke er udfyldt af en person, vil den udskrive rollens navn, og "mangler" rolletypen.
    //Ellers udskriver den rollens navn og hvem der spiller rollen.
    @Override
    public String toString(){
        if(spillesAf == null){
            return navn + " : mangler " + type;
        } else {
            return navn + " : " + spillesAf;
        }
    }

    //Hvis der er en ny rolle type, vil den blive lagt ind i den statiske liste "rolleTyper".
    public static void addRolleType(Rolle rolle){
        for(String rolleType : rolleTyper){
            if(rolleType.equals(rolle.getType())){
                return;
            }
        }
        rolleTyper.add(rolle.getType());
    }

    //Gettere og settere

    public UUID getRolleID() {
        return rolleID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Person getSpillesAf() {
        return spillesAf;
    }

    public void setSpillesAf(Person spillesAf) {
        this.spillesAf = spillesAf;
    }

    public static List<String> getRolleTyper() {
        return rolleTyper;
    }

    public static void setRolleTyper(List<String> rolleTyper) {
        Rolle.rolleTyper = rolleTyper;
    }
}
