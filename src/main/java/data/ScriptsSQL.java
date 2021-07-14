package data;

import org.hibernate.annotations.SQLInsert;

public class ScriptsSQL {

    private static String username;
    private static String password;
    private static String Produktion = "Insert INTO personer (fornavn, efternavn, nationalitet, dag, maaned, aar) VALUES (?,?,?,?,?,?)";
//    private static SQLInsert Produktion = Insert INTO personer (fornavn, efternavn, nationalitet, dag, maaned, aar) VALUES (?,?,?,?,?,?);

    void insertProduktion() {

    }

    void insertProducenter() {

    }

    void insertPersoner() {

    }


    boolean isLoginInformationCorrect(String username, String password) {
        return false;
    }

    boolean askToRegister() {

        return false;
    }
}
