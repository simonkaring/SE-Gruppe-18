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
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class editorController {
    //Initlaizer alle FXML objekterne
    @FXML private TableView<Person> tableView;
    @FXML private TableColumn fornavnColumn;
    @FXML private TableColumn efternavnColumn;
    @FXML private TableColumn alderColumn;
    @FXML private TableColumn nationalitetColumn;
    @FXML private TableColumn rolleColumn;
    @FXML private TextField fornavnTextField;
    @FXML private TextField efternavnTextField;
    @FXML private TextField nationalitetTextField;
    @FXML private TextField rolleTextField;
    @FXML private DatePicker dobDatePicker;
    @FXML private TableView<Rolle> rolleTableView;
     String title;


        //Metode til at tilføje alle medvirkende af et program ind i tableView tabellen
    public ObservableList<Person> getActors() {
        ObservableList<Person> actors = FXCollections.observableArrayList();
        ArrayList<Producent> producent = new ArrayList<>(KrediteringSystem.getSamletProducenter());
        for (Producent p  : producent ){
            ArrayList<Program> programs = new ArrayList<>(p.getProgrammer());
            for (Program pro : programs ){
               if (pro.getTitel().equals(title)){

               }
            }
        }
        return actors;
    }

    //Argumenter der sker når scenen initializer. Specificer hvad for nogle værdier de forskellige kolonner skal bruge.
    @FXML
    private void initialize() {
        fornavnColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("fornavn"));
        efternavnColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("efternavn"));
        alderColumn.setCellValueFactory(new PropertyValueFactory<Person, LocalDate>("alder"));
        nationalitetColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("nationalitet"));
        tableView.setItems(getActors());
        rolleColumn.setCellValueFactory(new PropertyValueFactory<>("navn"));
    }
    //Knap der tilføjer en ny medvirkende til programmet og fremviser det i listen.
    public void onAddButtonPush(){
        Person p = new Person (fornavnTextField.getText(), efternavnTextField.getText(),dobDatePicker.getValue(),nationalitetTextField.getText());
        tableView.getItems().add(p);
        Rolle r = new Rolle(rolleTextField.getText(), null, p);
        rolleTableView.getItems().add(r);
    }
    //Knap der fjerner en medvirkende i programmet og fjerner den medvirkende i listen.
    public void onRemoveButtonPush(){
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
        rolleTableView.getItems().removeAll(rolleTableView.getSelectionModel().getSelectedItems());
    }
    //Knap der henter dataen om en valgt medvirkende og udfylder den i tekstfelterne på højre side.
    public void onGetDataButtonPushed(){
        if (tableView.getSelectionModel().getSelectedItems() != null){

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
    public void onSetDataButtonPushed(){
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
        }
    }
    //Synkroniser den valgte medvirkende med den rigtige rolle i den anden tabel.
    public void SyncronizerolleTableView(){
        int i = tableView.getSelectionModel().getSelectedIndex();
        rolleTableView.getSelectionModel().select(i);
    }
    //Synkroniser den valgte rolle med den rigtige medvirkende i den anden tabel.
    public void SyncronizeTableView(){
        int i = rolleTableView.getSelectionModel().getSelectedIndex();
        tableView.getSelectionModel().select(i);
    }
    //Bruges til at få titel af programmet baseret fra valg i tidligere scene.
    public void getTitle(String s) {
        title = s;
    }
}


