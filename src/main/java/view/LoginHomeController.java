package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

// This class handles the home gui interface
public class LoginHomeController extends SceneChanger {

    // TitleHolder changes what a viewer and user sees
    TitleHolder holder = TitleHolder.getInstance();

    @FXML void loginAsUser(ActionEvent event) {
        System.out.println("Opening login side");
        changeScene("login.fxml");
    }

    @FXML void loginAsViewer(ActionEvent event) {
        System.out.println("Opening viewer mode");
        holder.setIsViewer(true);
        changeScene("production_viewer.fxml");
    }
}
