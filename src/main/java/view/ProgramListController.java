package view;

import com.jfoenix.controls.JFXButton;
import data.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.KrediteringSystem;
import model.*;
import model.Program;
import java.util.ArrayList;

public class ProgramListController {

    // Initialize objekter i GUI
    @FXML private ListView listView;
    @FXML public static TextField searchTextField;
    @FXML private TextField programNameTextField;
    @FXML private Button editCredits;
//    @FXML private Button seeCredits;
//    @FXML private Button searchButton;
    @FXML private Button addProgramButton;
    @FXML private JFXButton deleteCreditsButton;

    @FXML void logout(ActionEvent event) {
        System.out.println("Logging out. Opening login home scene");
        SceneChanger.changeScene("login_home.fxml");
    }

    TitleHolder holder = TitleHolder.getInstance();
    public ProgramListController() {
    }

    // Paramenter for initialization af programmet.
    @FXML private void initialize() {
        ArrayList<Producent> producent = new ArrayList<>(KrediteringSystem.getSamletProducenter());
        for (Producent p  : producent ){
            ArrayList<Program> programs = new ArrayList<>(p.getProgrammer());
            for (Program pro : programs ){
                listView.getItems().add(pro.getTitel());
            }
        }
        hideUIElement(holder.getIsViewer());
    }

    // Tilføjer program til listen af programmer under produceren "Placeholder".
    @FXML public void addProgramButtonPushed() {
        Producent producent = new Producent("Placeholder");
        int temp = producent.getProgrammer().size();
        String productionTitle = programNameTextField.getText();
        if(productionTitle != null) {
            producent.opretProgram(programNameTextField.getText());
            System.out.println("Added Production: " + productionTitle);
            listView.getItems().add(producent.getProgrammer().get(temp));
        }
    }

    // Åbner editoren på det valgte program
    @FXML public void openEditor() {
        if(listView.getSelectionModel().getSelectedItem() != null) {
            System.out.println("Opening production in editor mode");
            holder.setTitle(listView.getSelectionModel().getSelectedItem().toString());
            holder.setIsViewer(false);
            SceneChanger.openScenePopup("editor3.fxml", 800,600);
        }
    }

    // Åbner seer versionen af editor pagen, hvor dele af GUI er gemt.
    @FXML public void openViewerPage() {
        if(listView.getSelectionModel().getSelectedItem() != null) {
            System.out.println("Opening production in viewer mode");
            TitleHolder holder = TitleHolder.getInstance();
            holder.setTitle(listView.getSelectionModel().getSelectedItem().toString());
            holder.setIsViewer(true);
            SceneChanger.openScenePopup("editor3.fxml", 800, 600);
        }
    }

    // Removes GUI elements based on boolean value
    public void hideUIElement(boolean b) {
        if(b) {
            editCredits.setVisible(false);
            programNameTextField.setVisible(false);
            addProgramButton.setVisible(false);
            deleteCreditsButton.setVisible(false);
        }
    }

    // Search feature unfinished
    @FXML public void search() {
        Soeg.soegProgram(searchTextField.getText());
        ObservableList<Program> input = FXCollections.observableArrayList();
        ArrayList<Object> searchResults = new ArrayList<>(Soeg.getSoegeResultater());
        for(Object o : searchResults) {
            input.add((Program) o);
        }
        listView.setItems(input);
    }

    // Delete credits, destructive operation
    @FXML public void deleteCredits() {
        final Object selectedItem = listView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            String productionTitle = listView.getSelectionModel().getSelectedItem().toString();
            DeleteProduction.deleteProduction(productionTitle); // Executes SQL query from data layer
            System.out.println("Deleted Production: " + productionTitle);
            listView.getItems().remove(selectedItem);
        }
    }

}


