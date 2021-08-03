//package view;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.control.*;
//
//import java.io.IOException;
//
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ListView;
//import javafx.stage.Stage;
//import model.KrediteringSystem;
//import model.*;
//import model.Program;
//
//import java.util.ArrayList;
//
//
//public class ProductionListController {
//
//
//    //Initialize objekter i GUI
//    @FXML private ListView listView;
//    @FXML private TextField searchTextField;
//    @FXML private TextField programNameTextField;
//    @FXML private Button editCredits;
//    @FXML private Button seeCredits;
//    @FXML private Button searchButton;
//    @FXML private Button addProgramButton;
//    TitleHolder holder = TitleHolder.getInstance();
//    public ProductionListController() {
//    }
//    //Paramenter for initialization af programmet.
//    @FXML private void initialize() {
//        ArrayList<Producent> producent = new ArrayList<>(KrediteringSystem.getSamletProducenter());
//        for (Producent p  : producent ){
//            ArrayList<Program> programs = new ArrayList<>(p.getProgrammer());
//            for (Program pro : programs ){
//                listView.getItems().add(pro.getTitel());
//            }
//        }
//        hideUIElement(holder.getIsViewer());
//    }
//
//    // Tilføjer program til listen af programmer under produceren "Placeholder".
//    public void addProgramButtonPushed() {
//        Producent producent = new Producent("Placeholder");
//        int temp = producent.getProgrammer().size();
//        if(programNameTextField.getText() != null) {
//            producent.opretProgram(programNameTextField.getText());
//            listView.getItems().add(producent.getProgrammer().get(temp));
//        }
//    }
//    //Åbner editoren på det valgte program
//    public void openEditor() {
//        if(listView.getSelectionModel().getSelectedItem() != null) {
//            holder.setTitle(listView.getSelectionModel().getSelectedItem().toString());
//            holder.setIsViewer(false);
//            try {
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editor.fxml"));
//                Parent root = fxmlLoader.load();
//                Stage stage = new Stage();
//                stage.setScene(new Scene(root));
//                stage.show();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    //Åbner seer versionen af editor pagen, hvor dele af GUI er gemt.
//    public void openViewerPage() {
//        if(listView.getSelectionModel().getSelectedItem() != null) {
//            TitleHolder holder = TitleHolder.getInstance();
//            holder.setTitle(listView.getSelectionModel().getSelectedItem().toString());
//            holder.setIsViewer(true);
//            try {
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editor.fxml"));
//                Parent root = fxmlLoader.load();
//                Stage stage = new Stage();
//                stage.setScene(new Scene(root));
//                stage.show();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    //Fjerner del af GUI for bruger baseret på om de er en seer eller producer
//    public void hideUIElement(boolean b) {
//        if(b) {
//            editCredits.setVisible(false);
//            programNameTextField.setVisible(false);
//            addProgramButton.setVisible(false);
//        }
//    }
//    //Feature der ikke blev færdig implementeret
//    public void soeg() {
//        Soeg.soegProgram(searchTextField.getText());
//        ObservableList<Program> input = FXCollections.observableArrayList();
//        ArrayList<Object> searchResults = new ArrayList<>(Soeg.getSoegeResultater());
//        for(Object o : searchResults){
//            input.add((Program) o);
//        }
//        listView.setItems(input);
//    }
//}
//
//
