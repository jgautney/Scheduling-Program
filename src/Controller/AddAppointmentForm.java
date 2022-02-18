package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAppointmentForm implements Initializable {
    public TextField apptTF;
    public TextField titleTF;
    public TextField descTF;
    public TextField locationTF;
    public Button addButton;
    public Button cancelButton;
    public ComboBox startCombo;
    public ComboBox endCombo;
    public ComboBox custCombo;
    public ComboBox userCombo;
    public ComboBox contactCombo;
    public DatePicker datePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onAdd(ActionEvent actionEvent) {
        try{
            System.out.println("Appointment added!"); //placeholder FIXME
            Parent root = FXMLLoader.load(getClass().getResource("/View/appointmentForm.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Appointment Form");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onCancel(ActionEvent actionEvent) {
        try{
            System.out.println("Add Appointment Cancelled!"); //placeholder FIXME
            Parent root = FXMLLoader.load(getClass().getResource("/View/appointmentForm.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Appointment Form");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onStartCombo(ActionEvent actionEvent) {
    }

    public void onEndCombo(ActionEvent actionEvent) {
    }

    public void onCustCombo(ActionEvent actionEvent) {
    }

    public void onUserCombo(ActionEvent actionEvent) {
    }

    public void onContactCombo(ActionEvent actionEvent) {
    }

    public void onDatePicker(ActionEvent actionEvent) {
    }
}
