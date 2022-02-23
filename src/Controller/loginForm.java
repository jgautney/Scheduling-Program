package Controller;

import JDBC.JDBC;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.sql.*;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class loginForm implements Initializable{
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
        String inputName = usernameTF.getText();
        String inputPassword = passwordTF.getText();

        try{
            String sql = "SELECT User_Name, Password from users";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");

                if (Objects.equals(inputName, userName) && Objects.equals(inputPassword, password)) {

                    Parent root = FXMLLoader.load(getClass().getResource("/View/customerForm.fxml"));
                    Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    stage.setTitle("Customers");
                    stage.setScene(new Scene(root, 1000, 600));
                    stage.show();
                    return;
                    }
                }

                // If no successful login, show alert box
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText(rb.getString("match"));
                alert.show();
        }
        catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }

    public void onExit(ActionEvent actionEvent) {
        JDBC.closeConnection();
        System.exit(0);
    }

}
