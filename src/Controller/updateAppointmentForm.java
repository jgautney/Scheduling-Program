package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import JDBC.JDBC;
import Main.LocalDateTimeInterface;
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

/**
 * Controller for update appointment form
 */
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

    /**
     * Populates the text fields and combo boxes with the data that is carried over by the selectedAppointment object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            apptTF.setText(String.valueOf(selectedAppointment.getId()));
            titleTF.setText(selectedAppointment.getTitle());
            descTF.setText(selectedAppointment.getDescription());
            typeTF.setText(selectedAppointment.getType());
            locationTF.setText(selectedAppointment.getLocation());
            startCombo.setValue(selectedAppointment.getConvertedStart(selectedAppointment.getStart()));
            endCombo.setValue(selectedAppointment.getConvertedEnd(selectedAppointment.getEnd()));
            custCombo.setValue(selectedAppointment.getCustID());
            userCombo.setValue(selectedAppointment.getUserID());
            contactCombo.setValue(selectedAppointment.getContactID());
            datePicker.setValue(selectedAppointment.getStart().toLocalDate());
            populateComboBoxes();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * cancels updating appointment and returns to previous screen
     */
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

    /**
     * Updates appointment in the appointment table.
     *
     * Lambda expression is used twice in order to limit amount code that is needed when converting time
     * since it takes a few lines to convert each time.
     *
     * This method also uses a for loop with nested if statements to compare times to prevent any overlap between appointments
     * when the customer ID is the same.
     *
     * Method also contains the necessary SQL to update the appointment in the appointment table in the database.
     *
     * Throws exceptions containing error messages when certain things happen such as overlapping appointments or empty text fields
     *
     * @param actionEvent activated by selecting add button
     */
    public void onUpdate(ActionEvent actionEvent) {
        try{
            LocalTime startTime = (LocalTime) startCombo.getSelectionModel().getSelectedItem();
            LocalTime endTime = (LocalTime) endCombo.getSelectionModel().getSelectedItem();
            LocalDate startDate = datePicker.getValue();

            LocalDateTimeInterface timeStart = (t, d) -> LocalDateTime.of(d, t);

            LocalDateTimeInterface timeEnd = (t, d) -> LocalDateTime.of(d, t);

            ZoneId zoneId = ZoneId.of("America/New_York");
            ZoneId newZone = ZoneId.of("UTC");
            LocalDateTime newSDT = timeStart.convertLocalDateTime(startTime, startDate).atZone(zoneId).withZoneSameInstant(newZone).toLocalDateTime();
            LocalDateTime newEDT = timeEnd.convertLocalDateTime(endTime, startDate).atZone(zoneId).withZoneSameInstant(newZone).toLocalDateTime();

            ObservableList<Appointments> aTime = DBAppointments.getApptByID((Integer) custCombo.getValue());

            ZoneId dtz = ZoneId.systemDefault();
            ZoneId ctz = ZoneId.of("UTC");
            LocalDateTime csdt = newSDT.atZone(ctz).withZoneSameInstant(dtz).toLocalDateTime();
            LocalDateTime cedt = newEDT.atZone(ctz).withZoneSameInstant(dtz).toLocalDateTime();

            for (Appointments appointments : aTime){
                if(appointments.getId() !=  Integer.parseInt(apptTF.getText())){
                    if (appointments.getStart().toLocalDate().isEqual(startDate)) {

                        if ((appointments.getStart().isAfter(csdt) || appointments.getStart().equals(csdt) && appointments.getStart().isBefore(cedt))) {
                            throw new Exception();
                        }
                        if(appointments.getEnd().isAfter(csdt) && (appointments.getEnd().isBefore(csdt) || appointments.getEnd().equals(cedt))){
                            throw new Exception();
                        }
                        if((appointments.getStart().isBefore(csdt) || appointments.getStart().equals(csdt)) && (appointments.getEnd().isAfter(cedt) || appointments.getEnd().equals(cedt))){
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

    /**
     * method used for carrying data of the selected appointment from the previous screen
     * @param sa is the selected appointment
     */
    public static void updateAppointment(Appointments sa) {
        selectedAppointment = sa;
    }

    /**
     * displays the time in the end combo box offset by 15 minutes from the time set in the start combo box
     * @param actionEvent
     */
    public void onStartCombo(ActionEvent actionEvent) {
        LocalTime displayTime = (LocalTime)startCombo.getSelectionModel().getSelectedItem();
        displayTime = displayTime.plusMinutes(15);
        endCombo.setValue(displayTime);


        LocalTime start = displayTime;
        LocalTime end = LocalTime.of(21,0);

        while(start.isBefore(end.plusSeconds(1))){
            endCombo.getItems().add(start);
            start = start.plusMinutes(15);
        }
    }

    /**
     * Method used to reduce code in the initialize method
     */
    public void populateComboBoxes () {
        try {

            LocalTime start = LocalTime.of(8, 0);
            LocalTime end = LocalTime.of(21, 0);

            while (start.isBefore(end.plusSeconds(1))) {
                startCombo.getItems().add(start);
                endCombo.getItems().add(start);
                start = start.plusMinutes(15);
            }
            userCombo.setItems(DBUsers.getAllUsers());
            custCombo.setItems(DBCustomers.getAllCustomers());
            contactCombo.setItems(DBContacts.getAllContacts());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
