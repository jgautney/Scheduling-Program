package Controller;

import JDBC.JDBC;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import Model.Contacts;
import Model.Customers;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;


public class addAppointmentForm implements Initializable {
    public TextField apptTF;
    public TextField titleTF;
    public TextField descTF;
    public TextField locationTF;
    public TextField typeTF;
    public Button addButton;
    public Button cancelButton;
    public ComboBox startCombo;
    public ComboBox endCombo;
    public ComboBox<Customers> custCombo;
    public ComboBox<Users> userCombo;
    public ComboBox<Contacts> contactCombo;
    public DatePicker datePicker;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateComboBoxes();

    }

    public void onAdd(ActionEvent actionEvent) {

        try{
            LocalTime startTime = (LocalTime) startCombo.getSelectionModel().getSelectedItem();
            LocalDate startDate = datePicker.getValue();
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

            LocalTime endTime = (LocalTime) endCombo.getSelectionModel().getSelectedItem();
            LocalDateTime endDateTime = LocalDateTime.of(startDate, endTime);

            System.out.println(startDateTime);
            System.out.println(endDateTime);

            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
                    "VALUES('" + titleTF.getText() + "', '" + descTF.getText() + "', '" + locationTF.getText() + "', '" + typeTF.getText() +
                            "', '" + startDateTime + "', '" + endDateTime + "', '" + custCombo.getValue().getId() + "', '" +
                            userCombo.getValue().getId() + "', '" + contactCombo.getValue().getID() + "')";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();

            Parent root = FXMLLoader.load(getClass().getResource("/View/appointmentForm.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Appointment Form");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please fill out all fields");
            alert.show();
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
            LocalTime start = LocalTime.of(8, 0);
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
