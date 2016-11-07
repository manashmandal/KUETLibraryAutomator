package sample;

import Automator.User;
import com.sun.glass.events.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;


import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private User user;

    @FXML
    private Pane mainPane;

    @FXML
    private HBox hBoxLayout;

    @FXML
    private void handleButtonAction(ActionEvent event){
        user.debug();
    }

    @FXML
    private TextField textField;

    @FXML
    private Label label;

    public void setupBinding(StageStyle stageStyle){

    }



    @Override
    public void initialize(URL url, ResourceBundle rb){
//        String kundu = "051203026";
//        String kundupass = "Kundu123";
//        user = new User(kundu, kundupass);
//        user.login();
//        user.updateDetails();
//        user.debug();


    }
}
