package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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


    public void onLogin(ActionEvent actionEvent) throws IOException {
        System.out.println("login button pressed!");

        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        Stage stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Customers");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
    }

    public void onExit(ActionEvent actionEvent) {
        System.exit(0);
    }

}
