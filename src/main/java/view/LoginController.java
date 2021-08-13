package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static view.SceneChanger.*;


public class LoginController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    TitleHolder holder = TitleHolder.getInstance();

    @FXML
    void backToLoginHome(ActionEvent event) {
        System.out.println("Going back");
        changeScene("login_home.fxml");
    }

    @FXML
    void login(ActionEvent event) {
        System.out.println("Logging in");
        holder.setIsViewer(false);
        changeScene("production_viewer.fxml");
    }

    @FXML
    void register(ActionEvent event) {
        System.out.println("Opening register popup");
        holder.setIsViewer(false);
        openScenePopup("register.fxml", 600, 300);
    }
}
