package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class EditorController {
    //Initlaizer alle FXML objekterne
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn fornavnColumn;
    @FXML
    private TableColumn efternavnColumn;
    @FXML
    private TableColumn alderColumn;
    @FXML
    private TableColumn nationalitetColumn;
    @FXML
    private TableColumn rolleColumn;
    @FXML
    private TextField fornavnTextField;
    @FXML
    private TextField efternavnTextField;
    @FXML
    private TextField nationalitetTextField;
    @FXML
    private TextField rolleTextField;
    @FXML
    private DatePicker dobDatePicker;
    @FXML
    private TableView<Rolle> rolleTableView;
    @FXML private Button tilfojButton;
    @FXML private Button fjernButton;
    @FXML private Button hentButton;
    @FXML private Button saetButton;

    @FXML private Label fornavnLabel;
    @FXML private Label efternavnLabel;
    @FXML private Label dobLabel;
    @FXML private Label nationalitetLabel;
    @FXML private Label rolleLabel;

    Program currentProgram;

    //Metode til at tilføje alle medvirkende af et program ind i tableView tabellen
    public ObservableList<Person> getActors() {
        TitleHolder holder = TitleHolder.getInstance();
        System.out.println(holder.getTitle());
        ObservableList<Person> actors = FXCollections.observableArrayList();
        ArrayList<Producent> producent = new ArrayList<>(KrediteringSystem.getSamletProducenter());
        for (Producent p : producent) {
            ArrayList<Program> programs = new ArrayList<>(p.getProgrammer());
            for (Program pro : programs) {
                if (pro.getTitel().equals(holder.getTitle())) {
                    System.out.println("Finds program");
                    ArrayList<Rolle> rolle = new ArrayList<>(pro.getRollerIProgram());
                    System.out.println("Size of role array "+rolle.size());
                    for (Rolle role : rolle) {
                        System.out.println("Adds actor");
                        actors.add(role.getSpillesAf());
                        rolleTableView.getItems().add(role);
                    }
                }
            }
        }
        return actors;
    }
    public Program findProgram(){
        TitleHolder holder = TitleHolder.getInstance();
        ArrayList<Producent> producent = new ArrayList<>(KrediteringSystem.getSamletProducenter());
        for(Producent p : producent){
            ArrayList<Program> programs = new ArrayList<>(p.getProgrammer());
            for(Program pro : programs){
                if (pro.getTitel().equals(holder.getTitle())){
                    System.out.println("Sucessfully found program");
                    return pro;
                }
            }
        }
        System.out.println("Did not find matching program title");
        return null;
    }

    //Argumenter der sker når scenen initializer. Specificer hvad for nogle værdier de forskellige kolonner skal bruge.
    @FXML
    private void initialize() {
        fornavnColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("fornavn"));
        efternavnColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("efternavn"));
        alderColumn.setCellValueFactory(new PropertyValueFactory<Person, LocalDate>("alder"));
        nationalitetColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("nationalitet"));
        rolleColumn.setCellValueFactory(new PropertyValueFactory<>("navn"));
        tableView.setItems(getActors());
        Stage stage = new Stage();
        currentProgram = findProgram();
        System.out.println(TitleHolder.getInstance().getIsViewer());
        hideGuiElements(TitleHolder.getInstance().getIsViewer());
    }

    //Knap der tilføjer en ny medvirkende til programmet og fremviser det i listen.
    public void onAddButtonPush() {
        Person p = new Person(fornavnTextField.getText(), efternavnTextField.getText(), dobDatePicker.getValue(), nationalitetTextField.getText());
        tableView.getItems().add(p);
        //TODO Eventuelt tilføj ny tekstfield hvor man kan definer type af rolle eller erstat type med navn. (Burde man vise hvad navnet på rollen var, eller hvad for en type rolle det var?)
        Rolle r = new Rolle(rolleTextField.getText(), null, p);
        rolleTableView.getItems().add(r);
        currentProgram.addRolle(rolleTextField.getText(),null, p);
    }

    //Knap der fjerner en medvirkende i programmet og fjerner den medvirkende i listen.
    public void onRemoveButtonPush() {
        ObservableList<Rolle> temprolle = rolleTableView.getSelectionModel().getSelectedItems();
        ArrayList<Rolle> roller = new ArrayList<>(currentProgram.getRollerIProgram());
            for(Rolle rolle : roller){
                if(temprolle.get(0).getRolleID()==rolle.getRolleID()) {
                    System.out.println(temprolle.get(0).getSpillesAf().getFornavn() + " is the person thats getting removed");
                    System.out.println("Sucessfully removed the role!");
                    currentProgram.fjernRolle(rolle);

                }
            }
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
        rolleTableView.getItems().removeAll(rolleTableView.getSelectionModel().getSelectedItems());
    }

    //Knap der henter dataen om en valgt medvirkende og udfylder den i tekstfelterne på højre side.
    public void onGetDataButtonPushed() {
        if (tableView.getSelectionModel().getSelectedItems() != null) {

            int index = tableView.getSelectionModel().getSelectedIndex();
            Person person = tableView.getItems().get(index);
            fornavnTextField.setText(person.getFornavn());
            efternavnTextField.setText(person.getEfternavn());
            nationalitetTextField.setText(person.getNationalitet());
            dobDatePicker.setValue(person.getAlderDate());
            Rolle rolle = rolleTableView.getItems().get(index);
            rolleTextField.setText(rolle.getNavn());
        }
    }

    //Knap der erstatter data på en valgt medvirkende.
    public void onSetDataButtonPushed() {
        if (tableView.getSelectionModel().getSelectedItems() != null) {

            Person person = tableView.getSelectionModel().getSelectedItem();
            person.setFornavn(fornavnTextField.getText());
            person.setEfternavn(efternavnTextField.getText());
            person.setNationalitet(nationalitetTextField.getText());
            person.setAlder(dobDatePicker.getValue());
            tableView.getItems().add(tableView.getSelectionModel().getSelectedIndex(), person);
            tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex() - 1);
            tableView.refresh();
            Rolle rolle = rolleTableView.getSelectionModel().getSelectedItem();
            rolle.setNavn(rolleTextField.getText());
            rolleTableView.getItems().add(rolleTableView.getSelectionModel().getSelectedIndex(), rolle);
            rolleTableView.getItems().remove(rolleTableView.getSelectionModel().getSelectedIndex() - 1);
            rolleTableView.refresh();

            ArrayList<Rolle> roller = new ArrayList<>(currentProgram.getRollerIProgram());
                for(Rolle rolleliste : roller){
                    if (rolle.getRolleID()==rolleliste.getRolleID()){
                        int i = roller.indexOf(rolleliste);
                        roller.get(i).setNavn(rolleTextField.getText());
                        roller.get(i).setSpillesAf(person);
                    }
                }
        }
    }

    //Synkroniser den valgte medvirkende med den rigtige rolle i den anden tabel.
    public void SyncronizerolleTableView() {
        int i = tableView.getSelectionModel().getSelectedIndex();
        rolleTableView.getSelectionModel().select(i);
    }

    //Synkroniser den valgte rolle med den rigtige medvirkende i den anden tabel.
    public void SyncronizeTableView() {
        int i = rolleTableView.getSelectionModel().getSelectedIndex();
        tableView.getSelectionModel().select(i);
    }
    public void hideGuiElements(boolean b){
        if(b){
            fornavnTextField.setVisible(false);
            efternavnTextField.setVisible(false);
            nationalitetTextField.setVisible(false);
            rolleTextField.setVisible(false);
            dobDatePicker.setVisible(false);
            tilfojButton.setVisible(false);
            fjernButton.setVisible(false);
            hentButton.setVisible(false);
            saetButton.setVisible(false);
            fornavnLabel.setVisible(false);
            efternavnLabel.setVisible(false);
            dobLabel.setVisible(false);
            nationalitetLabel.setVisible(false);
            rolleLabel.setVisible(false);
        }
    }
}



