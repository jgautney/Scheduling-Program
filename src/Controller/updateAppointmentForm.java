package Controller;

import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import Model.Appointments;
import com.sun.javafx.scene.control.SelectedCellsMap;
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
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class updateAppointmentForm implements Initializable {
    public TextField apptTF;
    public TextField titleTF;
    public TextField descTF;
    public TextField locationTF;
    public Button cancelButton;
    public Button updateButton;
    public ComboBox startCombo;
    public ComboBox endCombo;
    public ComboBox custCombo;
    public ComboBox userCombo;
    public ComboBox contactCombo;
    public DatePicker datePicker;

    private static Appointments selectedAppointment = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            populateComboBoxes();
            apptTF.setText(String.valueOf(selectedAppointment.getId()));
            titleTF.setText(selectedAppointment.getTitle());
            descTF.setText(selectedAppointment.getDescription());
            locationTF.setText(selectedAppointment.getLocation());
            startCombo.setValue(selectedAppointment.getStart());
            endCombo.setValue(selectedAppointment.getEnd());
            custCombo.setValue(selectedAppointment.getCustID());
            userCombo.setValue(selectedAppointment.getUserID());
            contactCombo.setValue(selectedAppointment.getContactID());
            datePicker.setValue(selectedAppointment.getDate());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void onCancel(ActionEvent actionEvent) {
        try{

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

    public void onUpdate(ActionEvent actionEvent) {
        try{
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
    public static void updateAppointment(Appointments sa) {
        selectedAppointment = sa;
    }

    public void onStartCombo(ActionEvent actionEvent) {
        LocalTime displayTime = (LocalTime)startCombo.getSelectionModel().getSelectedItem();
        displayTime = displayTime.plusMinutes(60);
        endCombo.setValue(displayTime);


        LocalTime start = displayTime;
        LocalTime end = LocalTime.of(21,0);

        while(start.isBefore(end.plusSeconds(1))){
            endCombo.getItems().add(start);
            start = start.plusMinutes(60);
        }

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

    public void populateComboBoxes () {
        try {

            LocalTime start = LocalTime.of(8,0);
            LocalTime end = LocalTime.of(21, 0);

            while (start.isBefore(end.plusSeconds(1))) {
                startCombo.getItems().add(start);
                start = start.plusMinutes(60);
            }
            userCombo.setItems(DBUsers.getAllUsers());
            custCombo.setItems(DBCustomers.getAllCustomers());
            contactCombo.setItems(DBContacts.getAllContacts());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
