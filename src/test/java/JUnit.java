import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnit extends Application {

    static Stage stg;
    @Override public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        // Starts program with the login_home gui interface
        Parent root = FXMLLoader.load(getClass().getResource("login_home.fxml"));
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        model.KrediteringSystem.opstart();
        launch(args);
    }

    @Test
    void shouldShowSimpleAssertion() {
//        ProgramListController.searchTextField.setText("Test");
//        ProgramListController.searchTextField.
//        Assertions.assertNotNull(ProgramListController.searchTextField.getText());
//        Assertions.assertEquals(1, 1);
    }

    @Test
    public void testAdd() {
        String str = "Junit is working fine";
        Assertions.assertEquals("Junit is working fine", str);
    }

    @Test
    public void databaseInfoTest() {
        String test = "test";
        Assertions.assertNotNull(test);
//        Assertions.assertNotNull(QueryDatabase.selectProducer());
    }


}
