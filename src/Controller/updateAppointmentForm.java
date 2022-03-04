package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import JDBC.JDBC;
import Model.Appointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            LocalDateTime sdt = LocalDateTime.of(startDate, startTime);

            LocalTime endTime = (LocalTime) endCombo.getSelectionModel().getSelectedItem();
            LocalDateTime edt = LocalDateTime.of(startDate, endTime);

            ZoneId zoneId = ZoneId.of("America/New_York");
            ZoneId newZone = ZoneId.of("UTC");
            LocalDateTime newSDT = sdt.atZone(zoneId).withZoneSameInstant(newZone).toLocalDateTime();
            LocalDateTime newEDT = edt.atZone(zoneId).withZoneSameInstant(newZone).toLocalDateTime();

            ObservableList<Appointments> aTime = DBAppointments.getAllAppointments((Integer) custCombo.getValue());

            ZoneId dtz = ZoneId.systemDefault();
            ZoneId ctz = ZoneId.of("UTC");
            LocalDateTime csdt = newSDT.atZone(ctz).withZoneSameInstant(dtz).toLocalDateTime();
            LocalDateTime cedt = newEDT.atZone(ctz).withZoneSameInstant(dtz).toLocalDateTime();

            for (Appointments appointments : aTime){
                if(appointments.getId() !=  Integer.parseInt(apptTF.getText())){
                    System.out.println("ID's do not match!");
                    if (appointments.getDate().isEqual(startDate)) {

                        if ((appointments.getStart().isAfter(LocalTime.from(csdt)) || appointments.getStart().equals(LocalTime.from(csdt))) && appointments.getStart().isBefore(LocalTime.from(cedt))) {
                            throw new Exception();
                        }
                        if(appointments.getEnd().isAfter(LocalTime.from(csdt)) && (appointments.getEnd().isBefore(LocalTime.from(cedt)) || appointments.getEnd().equals(LocalTime.from(cedt)))){
                            throw new Exception();
                        }
                        if((appointments.getStart().isBefore(LocalTime.from(csdt)) || appointments.getStart().equals(LocalTime.from(csdt))) && (appointments.getEnd().isAfter(LocalTime.from(cedt)) || appointments.getEnd().equals(LocalTime.from(cedt)))){
                            throw new Exception();
                        }
                    }
                }
            }

            String sql = "UPDATE appointments SET Title = '" + titleTF.getText() + "', Description = '" + descTF.getText() + "', Location = '" + locationTF.getText() + "', " +
                    "Type = '" + typeTF.getText() + "', Start = '" + newSDT+ "', End = '" + newEDT + "', Customer_ID = '" + custCombo.getValue() + "', " +
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
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
        catch(RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please fill out all fields");
            alert.show();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Appointments can not overlap!");
            alert.show();
        }
    }
    public static void updateAppointment(Appointments sa) {
        selectedAppointment = sa;
    }

    public void onStartCombo(ActionEvent actionEvent) {
        LocalTime displayTime = (LocalTime)startCombo.getSelectionModel().getSelectedItem();
        displayTime = displayTime.plusMinutes(30);
        endCombo.setValue(displayTime);


        LocalTime start = displayTime;
        LocalTime end = LocalTime.of(21,0);

        while(start.isBefore(end.plusSeconds(1))){
            endCombo.getItems().add(start);
            start = start.plusMinutes(30);
        }
    }

    public void populateComboBoxes () {
        try {

            LocalTime start = LocalTime.of(8,0);
            LocalTime end = LocalTime.of(21, 0);

            while (start.isBefore(end.plusSeconds(1))) {
                startCombo.getItems().add(start);
                endCombo.getItems().add(start);
                start = start.plusMinutes(30);
            }
            userCombo.setItems(DBUsers.getAllUsers());
            custCombo.setItems(DBCustomers.getAllCustomers());
            contactCombo.setItems(DBContacts.getAllContacts());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
