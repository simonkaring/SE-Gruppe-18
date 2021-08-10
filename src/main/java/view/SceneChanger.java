package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {

    public static void changeScene(String fxml) {
        // This method changes scene and can be called with 'extends SceneChanger' on the class
        Parent window1;
        try {
            window1 = FXMLLoader.load(SceneChanger.class.getResource(fxml));
            Stage window1Stage;
            Scene window1Scene = new Scene(window1, 1000, 800);
            window1Stage = Main.stg;
            window1Stage.setScene(window1Scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openScenePopup(String fxml, int horizontal, int vertical) {
        // Opens a new scene like a popup
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SceneChanger.class.getResource(fxml));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, horizontal, vertical));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
