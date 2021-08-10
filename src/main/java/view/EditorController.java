package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.TableColumn;

public class EditorController extends SceneChanger {

    // Initializes all FXML objects
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableView<Role> rolleTableView;
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

////////////////////////////////////////////////////////////////////////////////
//    Tree table version needs looking at. Using default table instead
//    @FXML private JFXTreeTableView<Person> tableView;
//    @FXML private JFXTreeTableView<Rolle> rolleTableView;
//    @FXML private TreeTableColumn<?, ?> fornavnColumn;
//    @FXML private TreeTableColumn<?, ?> efternavnColumn;
//    @FXML private TreeTableColumn<?, ?> alderColumn;
//    @FXML private TreeTableColumn<?, ?> nationalitetColumn;
//    @FXML private TreeTableColumn<?, ?> rolleColumn;
////////////////////////////////////////////////////////////////////////////////

    @FXML
    private Button addNewPersonButton;
    @FXML
    private Button removeSelectedPersonButton;
    @FXML
    private Button retrievePersonDataButton;
    @FXML
    private Button savePersonDataButton;

    @FXML
    private Text fornavnLabel;
    @FXML
    private Text efternavnLabel;
    @FXML
    private Text dobLabel;
    @FXML
    private Text nationalitetLabel;
    @FXML
    private Text rolleLabel;

    Production currentProduction;

    //Metode til at tilføje alle medvirkende af et program ind i tableView tabellen
    public ObservableList<Person> getActors() {
        TitleHolder holder = TitleHolder.getInstance();
        System.out.println("Production Title: " + holder.getTitle());
        ObservableList<Person> actors = FXCollections.observableArrayList();
        ArrayList<Producer> producer = new ArrayList<>(KrediteringSystem.getSamletProducenter());
        for (Producer p : producer) {
            ArrayList<Production> productions = new ArrayList<>(p.getProgrammer());
            for (Production pro : productions) {
                if (pro.getTitel().equals(holder.getTitle())) {
                    ArrayList<Role> rolle = new ArrayList<>(pro.getRollerIProgram());
                    System.out.println("Size of role array = " + rolle.size());
                    for (Role role : rolle) {
//                        System.out.println("Actor added");
                        actors.add(role.getSpillesAf());
                        rolleTableView.getItems().add(role);
                    }
                }
            }
        }
        return actors;
    }

    // Metode der finder det valgte program fra tidligere GUI
    public static Production findProduction() {
        TitleHolder holder = TitleHolder.getInstance();
        ArrayList<Producer> producer = new ArrayList<>(KrediteringSystem.getSamletProducenter());
        for (Producer p : producer) {
            ArrayList<Production> productions = new ArrayList<>(p.getProgrammer());
            for (Production pro : productions) {
                if (pro.getTitel().equals(holder.getTitle())) {
                    System.out.println("Successfully found production");
                    return pro;
                }
            }
        }
        System.out.println("Did not find matching production title");
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
        currentProduction = findProduction();
        tableView.setItems(getActors());
        hideGuiElements(TitleHolder.getInstance().getIsViewer());
    }

    //Knap der tilføjer en ny medvirkende til programmet og fremviser det i listen.
    public void addNewPerson() {
        Person p = new Person(fornavnTextField.getText(), efternavnTextField.getText(), dobDatePicker.getValue(), nationalitetTextField.getText());
        tableView.getItems().add(p);
        //TODO Eventuelt tilføj ny tekstfield hvor man kan definer type af rolle eller erstat type med navn. (Burde man vise hvad navnet på rollen var, eller hvad for en type rolle det var?)
        Role r = new Role(rolleTextField.getText(), null, p);
        rolleTableView.getItems().add(r);
        currentProduction.addRolle(rolleTextField.getText(), null, p);
        System.out.println("New person added to production");
    }

    //Knap der fjerner en medvirkende i programmet og fjerner den medvirkende i listen.
    public void removeSelectedPerson() {
        ObservableList<Role> temprolle = rolleTableView.getSelectionModel().getSelectedItems();
        ArrayList<Role> roller = new ArrayList<>(currentProduction.getRollerIProgram());
        for (Role role : roller) {
            if (temprolle.get(0).getRolleID() == role.getRolleID()) {
                System.out.println("Removing " + temprolle.get(0).getSpillesAf().getFornavn());
                System.out.println("Role successfully removed");
                currentProduction.fjernRolle(role);
            }
        }
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
        rolleTableView.getItems().removeAll(rolleTableView.getSelectionModel().getSelectedItems());
    }

    //Knap der henter dataen om en valgt medvirkende og udfylder den i tekstfelterne på højre side.
    public void retrievePersonData() {
        if (tableView.getSelectionModel().getSelectedItems() != null) {
            int index = tableView.getSelectionModel().getSelectedIndex();
            Person person = tableView.getItems().get(index);
            fornavnTextField.setText(person.getFornavn());
            efternavnTextField.setText(person.getEfternavn());
            nationalitetTextField.setText(person.getNationalitet());
            dobDatePicker.setValue(person.getAlderDate());
            Role role = rolleTableView.getItems().get(index);
            rolleTextField.setText(role.getNavn());
            System.out.println();
        }
    }

    //Knap der erstatter data på en valgt medvirkende.
    public void savePersonData() {
        if (tableView.getSelectionModel().getSelectedItems() != null) {

            Person person = tableView.getSelectionModel().getSelectedItem();
            person.setFornavn(fornavnTextField.getText());
            person.setEfternavn(efternavnTextField.getText());
            person.setNationalitet(nationalitetTextField.getText());
            person.setAlder(dobDatePicker.getValue());
            tableView.getItems().add(tableView.getSelectionModel().getSelectedIndex(), person);
            tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex() - 1);
            tableView.refresh();
            Role role = rolleTableView.getSelectionModel().getSelectedItem();
            role.setNavn(rolleTextField.getText());
            rolleTableView.getItems().add(rolleTableView.getSelectionModel().getSelectedIndex(), role);
            rolleTableView.getItems().remove(rolleTableView.getSelectionModel().getSelectedIndex() - 1);
            rolleTableView.refresh();

            ArrayList<Role> roller = new ArrayList<>(currentProduction.getRollerIProgram());
            for (Role rolleliste : roller) {
                if (role.getRolleID() == rolleliste.getRolleID()) {
                    int i = roller.indexOf(rolleliste);
                    roller.get(i).setNavn(rolleTextField.getText());
                    roller.get(i).setSpillesAf(person);
                }
            }
        }
    }

    // Synchronize
    //Synkroniser den valgte medvirkende med den rigtige rolle i den anden tabel.
    public void synchronizeRoleTableView() {
        int i = tableView.getSelectionModel().getSelectedIndex();
        rolleTableView.getSelectionModel().select(i);
    }

    //Synkroniser den valgte rolle med den rigtige medvirkende i den anden tabel.
    public void synchronizeTableView() {
        int i = rolleTableView.getSelectionModel().getSelectedIndex();
        tableView.getSelectionModel().select(i);
    }

    //Metode der gemmer dele af GUI baseret på en boolean
    public void hideGuiElements(boolean b) {
        if (b) {
            fornavnTextField.setVisible(false);
            efternavnTextField.setVisible(false);
            nationalitetTextField.setVisible(false);
            rolleTextField.setVisible(false);
            dobDatePicker.setVisible(false);
            addNewPersonButton.setVisible(false);
            removeSelectedPersonButton.setVisible(false);
            retrievePersonDataButton.setVisible(false);
            savePersonDataButton.setVisible(false);
            fornavnLabel.setVisible(false);
            efternavnLabel.setVisible(false);
            dobLabel.setVisible(false);
            nationalitetLabel.setVisible(false);
            rolleLabel.setVisible(false);
//            AnchorPane anchorPane = new AnchorPane();
//            anchorPane.setLeftAnchor(tableView, 0.0);
        }
    }
}



