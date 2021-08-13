package view;

import com.jfoenix.controls.JFXButton;
import data.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.KrediteringSystem;
import model.*;
import model.Production;
import java.util.ArrayList;

import static view.SceneChanger.*;

public class ProductionListController {

    // Initialize objekter i GUI
    @FXML
    private ListView listView;
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField programNameTextField;
    @FXML
    private Button editCredits;
    //    @FXML private Button seeCredits;
//    @FXML private Button searchButton;
    @FXML
    private Button addProgramButton;
    @FXML
    private JFXButton deleteCreditsButton;

    @FXML
    void logout() {
        System.out.println("Logging out. Opening login home scene");
        changeScene("login_home.fxml");
    }

    TitleHolder holder = TitleHolder.getInstance();

    public ProductionListController() {
    }

    private final ArrayList productionList = new ArrayList();

    // Paramenter for initialization af programmet.
    @FXML
    private void initialize() {
        ArrayList<Producer> producer = new ArrayList<>(KrediteringSystem.getSamletProducenter());
        for (Producer p : producer) {
            ArrayList<Production> productions = new ArrayList<>(p.getProgrammer());
            for (Production pro : productions) {
//                System.out.println(pro.getTitel());
                listView.getItems().add(pro.getTitel());
                productionList.add(pro.getTitel());
            }
        }
        hideUIElement(holder.getIsViewer());
    }

    // Adds production to a list under "Placeholder"
    @FXML
    public void addProduction() {
        Producer producer = new Producer("Placeholder");
        int temp = producer.getProgrammer().size();
        String productionTitle = programNameTextField.getText();
        if (productionTitle != null) {
            producer.opretProgram(programNameTextField.getText());
            System.out.println("Added Production: " + productionTitle);
            listView.getItems().add(producer.getProgrammer().get(temp));
        }
    }

    // Åbner editoren på det valgte program
    @FXML
    public void editProduction() {
        if (listView.getSelectionModel().getSelectedItem() != null) {
            System.out.println("Opening production in editor mode");
            holder.setTitle(listView.getSelectionModel().getSelectedItem().toString());
            holder.setIsViewer(false);
            openScenePopup("editor.fxml", 800, 600);
        }
    }

    // Åbner seer versionen af editor pagen, hvor dele af GUI er gemt.
    @FXML
    public void viewProduction() {
        if (listView.getSelectionModel().getSelectedItem() != null) {
            System.out.println("Opening production in viewer mode");
            TitleHolder holder = TitleHolder.getInstance();
            holder.setTitle(listView.getSelectionModel().getSelectedItem().toString());
            holder.setIsViewer(true);
            openScenePopup("editor.fxml", 800, 600);
        }
    }

    // Removes GUI elements based on boolean value
    public void hideUIElement(boolean b) {
        if (b) {
            editCredits.setVisible(false);
            programNameTextField.setVisible(false);
            addProgramButton.setVisible(false);
            deleteCreditsButton.setVisible(false);
        }
    }

    // Searches from the list of programs and finds matches and partial matches
    @FXML
    public void searchProductions() {
        String searchText = searchTextField.getText();
        if (searchTextField.textProperty().get().isEmpty()) {
            // Restore all items if there is an empty search field
            System.out.println("Empty search field, restoring program list");
            listView.getItems().clear();
            for (int i = 0; i < productionList.size(); i++) {
                listView.getItems().add(productionList.get(i));
            }
        } else if (searchText != null) {
            System.out.println("Searching with: " + searchText);
            listView.getItems().clear();
            int matchesNumber = 0;
            for (int i = 0; i < productionList.size(); i++) {
                String cellValue = productionList.get(i).toString();
                cellValue = cellValue.toLowerCase();
                if (cellValue.contains(searchTextField.textProperty().get().toLowerCase())) {
                    matchesNumber++;
                    listView.getItems().add(productionList.get(i));
                }
            }
            System.out.println("Number of matches: " + matchesNumber);
        }

        // Old search kept for future review

//        Soeg.soegProgram(searchTextField.getText());
//        ObservableList<Program> input = FXCollections.observableArrayList();
//        ArrayList<Object> searchResults = new ArrayList<>(Soeg.getSoegeResultater());
//        for(Object o : searchResults) {
//            input.add((Program) o);
//        }
//        listView.setItems(input);
    }

    // Deletes the production, destructive operation
    @FXML
    public void deleteProduction() {
        final Object selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String productionTitle = listView.getSelectionModel().getSelectedItem().toString();
            QueryDatabase.deleteProduction(productionTitle); // Executes SQL query from data layer
            System.out.println("Deleted Production: " + productionTitle);
            listView.getItems().remove(selectedItem);
        }
    }
}


