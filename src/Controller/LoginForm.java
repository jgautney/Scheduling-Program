package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;


import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LoginForm implements Initializable{
    public TextField usernameTF;
    public TextField passwordTF;
    public Button loginButton;
    public Button exitButton;
    public Label timeLabel;


    //ZoneId zoneid = TimeZone.getDefault().toZoneId();
    String zoneId = TimeZone.getDefault().getID();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    timeLabel.setText(String.valueOf(zoneId));

    }


    public void onLogin(ActionEvent actionEvent) {
        System.out.println("login button pressed!");
    }

    public void onExit(ActionEvent actionEvent) {
        System.exit(0);
    }

}
