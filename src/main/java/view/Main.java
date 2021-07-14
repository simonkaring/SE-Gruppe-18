package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.KrediteringSystem;

public class Main extends Application {

    static Stage stg;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stg = primaryStage;
        // Starts program with the login_home gui interface
        Parent root = FXMLLoader.load(getClass().getResource("login_home.fxml"));
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        model.KrediteringSystem.opstart();
        launch(args);

    }
//    public static void main(String[] args) {
//        Main.main(args);
//    }

}
