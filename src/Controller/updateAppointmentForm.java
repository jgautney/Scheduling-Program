package Controller;

import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import JDBC.JDBC;
import Model.Appointments;
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
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class updateAppointmentForm implements Initializable {
    public TextField apptTF;
    public TextField titleTF;
    public TextField descTF;
    public TextField typeTF;
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
            typeTF.setText(selectedAppointment.getType());
            locationTF.setText(selectedAppointment.getLocation());
            startCombo.setValue(selectedAppointment.getConvertedStart(selectedAppointment.getStart(), selectedAppointment.getDate()));
            endCombo.setValue(selectedAppointment.getConvertedEnd(selectedAppointment.getEnd(), selectedAppointment.getDate()));
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
            LocalTime startTime = (LocalTime) startCombo.getSelectionModel().getSelectedItem();
            LocalDate startDate = datePicker.getValue();
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

            ZoneId startZoneId = ZoneId.of("America/New_York");
            ZoneId startZone = ZoneId.of("UTC");
            LocalDateTime newStartDateTime = startDateTime.atZone(startZoneId).withZoneSameInstant(startZone).toLocalDateTime();

            LocalTime endTime = (LocalTime) endCombo.getSelectionModel().getSelectedItem();
            LocalDateTime endDateTime = LocalDateTime.of(startDate, endTime);

            ZoneId endZoneId = ZoneId.of("America/New_York");
            ZoneId endZone = ZoneId.of("UTC");
            LocalDateTime newEndDateTime = endDateTime.atZone(endZoneId).withZoneSameInstant(endZone).toLocalDateTime();


            String sql = "UPDATE appointments SET Title = '" + titleTF.getText() + "', Description = '" + descTF.getText() + "', Location = '" + locationTF.getText() + "', " +
                    "Type = '" + typeTF.getText() + "', Start = '" + newStartDateTime + "', End = '" + newEndDateTime + "', Customer_ID = '" + custCombo.getValue() + "', " +
                    "User_ID = '" + userCombo.getValue() + "', Contact_ID = '" + contactCombo.getValue() + "' " +
                    " WHERE Appointment_ID=" + apptTF.getText();

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();

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
