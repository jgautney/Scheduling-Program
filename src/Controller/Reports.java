package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Reports implements Initializable {
    public ComboBox contactCombo;
    public TableView contactDataTable;
    public ComboBox monthCombo;
    public ComboBox typeCombo;
    public Label monthtypeLabel;
    public Label custNumberLabel;
    public Button backButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onContactSelect(ActionEvent actionEvent) {
    }

    public void onMonth(ActionEvent actionEvent) {
    }

    public void onType(ActionEvent actionEvent) {
    }

    public void onBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentForm.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Appointment Form");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
    }
}
