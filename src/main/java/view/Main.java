package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static Stage stg;
    @Override public void start(Stage primaryStage) throws Exception {
        this.stg = primaryStage;
        // Starts program with the login_home gui interface
        System.out.println("Opening GUI");
        Parent root = FXMLLoader.load(getClass().getResource("login_home.fxml"));
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
//        System.out.println("");
        model.KrediteringSystem.opstart();
        launch(args);
    }

}
