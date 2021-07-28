package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger
{

    void changeScene(String fxml) {
        // This method changes scene and can be called with 'extends SceneChanger' on the class
        Parent window1;
        try {
            window1 = FXMLLoader.load(getClass().getResource(fxml));
            Stage window1Stage;
            Scene window1Scene = new Scene(window1, 1000, 800);
            window1Stage = Main.stg;
            window1Stage.setScene(window1Scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void openScenePopup(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editor.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
