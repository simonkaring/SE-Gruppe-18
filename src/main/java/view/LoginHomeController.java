package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

// This class handles the home gui interface
public class LoginHomeController extends SceneChanger {
    // TitleHolder changes what a viewer and user sees
    TitleHolder holder = TitleHolder.getInstance();

    @FXML void loginAsUser(ActionEvent event) {
        changeScene("login2.fxml");
    }

    @FXML void loginAsViewer(ActionEvent event) {
        holder.setIsViewer(true);
        changeScene("scene3.fxml");
    }
}
