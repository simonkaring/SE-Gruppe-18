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
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class LoginController {
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    TitleHolder holder = TitleHolder.getInstance();

    public void loginSystem(){
        if(usernameTextField.getText().equals("admin") && passwordField.getText().equals("admin")){
            holder.setIsViewer(false);
            Stage s = (Stage) loginButton.getScene().getWindow();
            s.close();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText("Forkert username eller password");
            alert.showAndWait();
        }
    }

    public void loginAsSeer(){
        holder.setIsViewer(true);
        Stage s = (Stage) loginButton.getScene().getWindow();
        s.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
