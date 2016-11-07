package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Automator.User;




public class Main extends Application {

    final int max_width = 400;
    final int min_width = 383;
    final int max_height = 250;
    final int min_height = 200;
    final int height = 250;

    String kundu = "051203026";
    String kundupass = "Kundu123";
    String kanakpass = "398381";


    String manash = "051203043";


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("details.fxml"));
        primaryStage.setTitle("KUET Library Automator");
        Scene scene = new Scene(root);
//        primaryStage.setMaxWidth(max_width);
//        primaryStage.setMinWidth(min_width);
//        primaryStage.setMaxHeight(max_height);
//        primaryStage.setMinHeight(min_height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
