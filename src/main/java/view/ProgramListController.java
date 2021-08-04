package view;

import com.jfoenix.controls.JFXButton;
import data.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.KrediteringSystem;
import model.*;
import model.Program;
import javafx.scene.control.*;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TableColumn;
import java.util.ArrayList;


public class ProgramListController {


    //Initialize objekter i GUI
    @FXML private ListView listView;
    @FXML private TextField searchTextField;
    @FXML private TextField programNameTextField;
    @FXML private Button editCredits;
//    @FXML private Button seeCredits;
//    @FXML private Button searchButton;
    @FXML private Button addProgramButton;
    @FXML private JFXButton deleteCreditsButton;
    @FXML void logout(ActionEvent event) {
        SceneChanger.changeScene("login_home.fxml");
    }

    TitleHolder holder = TitleHolder.getInstance();
    public ProgramListController() {
    }
    //Paramenter for initialization af programmet.
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
        if(programNameTextField.getText() != null) {
            producent.opretProgram(programNameTextField.getText());
            listView.getItems().add(producent.getProgrammer().get(temp));
        }
    }
    //Åbner editoren på det valgte program
    @FXML public void openEditor() {
        if(listView.getSelectionModel().getSelectedItem() != null) {
            holder.setTitle(listView.getSelectionModel().getSelectedItem().toString());
            holder.setIsViewer(false);
            try {
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editor.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editor3.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //Åbner seer versionen af editor pagen, hvor dele af GUI er gemt.
    @FXML public void openViewerPage() {
        if(listView.getSelectionModel().getSelectedItem() != null) {
            TitleHolder holder = TitleHolder.getInstance();
            holder.setTitle(listView.getSelectionModel().getSelectedItem().toString());
            holder.setIsViewer(true);
            try {
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editor.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editor3.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        for(Object o : searchResults){
            input.add((Program) o);
        }
        listView.setItems(input);
    }

    @FXML public void deleteCredits() {
        if(listView.getSelectionModel().getSelectedItem() != null) {
//            holder.setTitle(listView.getSelectionModel().getSelectedItem().toString());
            String production = listView.getSelectionModel().getSelectedItem().toString();
            DeleteProduction.deleteProduction(production); // Executes SQL query from data layer
        }
    }

}


