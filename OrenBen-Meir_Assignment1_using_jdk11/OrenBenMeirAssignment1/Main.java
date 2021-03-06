package OrenBenMeirAssignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Assignment 1: Target-Heart-Rate Calculator");
        primaryStage.setScene(new Scene(root, 630, 550));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
