package Controller;

import DBAccess.DBAppointments;
import JDBC.JDBC;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.*;
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
            LocalDateTime sdt = LocalDateTime.of(startDate, startTime);

            LocalTime endTime = (LocalTime) endCombo.getSelectionModel().getSelectedItem();
            LocalDateTime edt = LocalDateTime.of(startDate, endTime);

            ZoneId zoneId = ZoneId.of("America/New_York");
            ZoneId newZone = ZoneId.of("UTC");
            LocalDateTime newSDT = sdt.atZone(zoneId).withZoneSameInstant(newZone).toLocalDateTime();
            LocalDateTime newEDT = edt.atZone(zoneId).withZoneSameInstant(newZone).toLocalDateTime();


             ObservableList<Appointments> aTime = DBAppointments.getAllAppointments(custCombo.getValue().getId());

             ZoneId dtz = ZoneId.systemDefault();
             ZoneId ctz = ZoneId.of("UTC");
             LocalDateTime csdt = newSDT.atZone(ctz).withZoneSameInstant(dtz).toLocalDateTime();
             LocalDateTime cedt = newEDT.atZone(ctz).withZoneSameInstant(dtz).toLocalDateTime();


            for (Appointments appointments : aTime) {
                if (appointments.getDate().isEqual(startDate)) {
                    System.out.println("Dates Match!!");
                    System.out.println(appointments.getStart());
                    System.out.println(LocalTime.from(csdt));
                    System.out.println(appointments.getEnd());
                    System.out.println(LocalTime.from(cedt));

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


            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
                    "VALUES('" + titleTF.getText().trim() + "', '" + descTF.getText().trim() + "', '" + locationTF.getText().trim() + "', '" + typeTF.getText().trim() +
                            "', '" + newSDT + "', '" + newEDT + "', '" + custCombo.getValue().getId() + "', '" +
                            userCombo.getValue().getId() + "', '" + contactCombo.getValue().getID() + "')";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();

            Parent root = FXMLLoader.load(getClass().getResource("/View/appointmentForm.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Appointment Form");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        }
        catch (SQLException | IOException throwables) {
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
            LocalTime start = LocalTime.of(8, 0);
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
