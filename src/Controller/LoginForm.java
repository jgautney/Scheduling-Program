package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;


import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LoginForm implements Initializable{
    public TextField usernameTF;
    public TextField passwordTF;
    public Button loginButton;
    public Button exitButton;
    public Label timeLabel;
    public Label usernameLabel;
    public Label passwordLabel;
    public Label loginLabel;


    String zoneId = TimeZone.getDefault().getID();

    Locale locale = Locale.getDefault();
    ResourceBundle rb = ResourceBundle.getBundle("Labels", locale);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    timeLabel.setText(String.valueOf(zoneId));
    usernameLabel.setText(rb.getString("username"));
    passwordLabel.setText(rb.getString("password"));
    loginLabel.setText(rb.getString("login"));
    loginButton.setText(rb.getString("login"));
    exitButton.setText(rb.getString("exit"));

    }


    public void onLogin(ActionEvent actionEvent) {
        System.out.println("login button pressed!");
    }

    public void onExit(ActionEvent actionEvent) {
        System.exit(0);
    }

}
