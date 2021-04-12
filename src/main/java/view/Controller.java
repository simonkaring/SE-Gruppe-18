package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.Observable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;


public class Controller {


    //Initialize objects in GUI
    @FXML private ListView listView;
    @FXML private TextArea textArea;
    @FXML private Button jButton;

    public Controller() throws IOException {
    }

    @FXML private void initialize()
    {
        listView.getItems().addAll("Test","Test2","Test3","Test4","Test5");
    }

    public void listViewButtonPushed(){
        String textAreaString = "";

        ObservableList listOfItems = this.listView.getSelectionModel().getSelectedItems();

        for (Object item : listOfItems)
        {
            textAreaString += String.format("%s%n",(String) item);
        }

        textArea.setText(textAreaString);
    }



    //Code for adding stuff to listView


}
