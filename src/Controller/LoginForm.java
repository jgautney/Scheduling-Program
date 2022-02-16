package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginForm implements Initializable{
    public TextField usernameTF;
    public TextField passwordTF;
    public Button loginButton;
    public Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void onLogin(ActionEvent actionEvent) {
        System.out.println("login button pressed!");
    }

    public void onExit(ActionEvent actionEvent) {
        System.exit(0);
    }

}
