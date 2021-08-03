package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class LoginController {
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    TitleHolder holder = TitleHolder.getInstance();

    @FXML void backToLoginHome(ActionEvent event) {
        SceneChanger.changeScene("login_home.fxml");
    }

    @FXML void login(ActionEvent event) {
        holder.setIsViewer(false);
        SceneChanger.changeScene("scene3.fxml");
    }

    @FXML void register(ActionEvent event) {
        holder.setIsViewer(false);
        SceneChanger.changeScene("scene2.fxml");
    }

//    public void loginSystem(){
//        if(usernameTextField.getText().equals("admin") && passwordField.getText().equals("admin")){
//            holder.setIsViewer(false);
//            changeScene("scene.fxml");
//        }
//        else{
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Fejl");
//            alert.setHeaderText("Forkert username eller password");
//            alert.showAndWait();
//        }
//    }

//    public void loginAsSeer(){
//        holder.setIsViewer(true);
//        changeScene("scene.fxml");
//    }

}
