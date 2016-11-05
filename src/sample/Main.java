package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Automator.User;

public class Main extends Application {

    String kundu = "051203026";
    String kundupass = "Kundu123";
    String kanakpass = "398381";


    String manash = "051203043";


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
//        User user = new User("051203026", "Kundu123");
        User user = new User(kundu, kundupass);
        user.login();
        user.updateDetails();
        user.debug();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
