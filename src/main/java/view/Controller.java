package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Observable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.KrediteringSystem;
import model.Producent;
import model.Program;

import java.util.ArrayList;


public class Controller {


    //Initialize objekter i GUI
    @FXML private ListView listView;
    @FXML private TextField searchTextField;
    @FXML private TextField programNameTextField;
    @FXML private Button editCredits;
    @FXML private Button seeCredits;
    @FXML private Button searchButton;
    @FXML private Button addProgramButton;

    public Controller() throws IOException {
    }
    //Paramenter for initialization af programmet.
    @FXML private void initialize()
    {
        ArrayList<Producent> producent = new ArrayList<>(KrediteringSystem.getSamletProducenter());
        for (Producent p  : producent ){
            ArrayList<Program> programs = new ArrayList<>(p.getProgrammer());
            for (Program pro : programs ){
                listView.getItems().add(pro.getTitel());
            }
        }
    }

    // Tilføjer program til listen af programmer under produceren "Placeholder".
    public void addProgramButtonPushed() {
        Producent producent = new Producent("Placeholder");
        int temp = producent.getProgrammer().size();
        if(programNameTextField.getText() != null) {
            producent.opretProgram(programNameTextField.getText());
            listView.getItems().add(producent.getProgrammer().get(temp));
        }
    }
    //Åbner editoren på det valgte program
    public void openEditor(){
        if(listView.getSelectionModel().getSelectedItem() != null) {
            try {
                String title = listView.getSelectionModel().getSelectedItem().toString();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editor.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                editorController ect = fxmlLoader.getController();
                ect.getTitle(title);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
