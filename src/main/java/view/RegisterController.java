package view;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class RegisterController extends SceneChanger {

    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameTextField;

    @FXML
    void register(ActionEvent event) {
        System.out.println("Registered click with");
        System.out.println("Username: " + usernameTextField.getText());
        System.out.println("Password: " + passwordField.getText());
    }
}
