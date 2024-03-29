package Controller;

import JDBC.JDBC;
import DBAccess.DBAppointments;
import Model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for appointment form
 */

public class appointmentForm implements Initializable {
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public Button customerButton;

    public TableColumn idCol;
    public TableColumn titleCol;
    public TableColumn descriptionCol;
    public TableColumn typeCol;
    public TableColumn startCol;
    public TableColumn endCol;
    public TableColumn custIdCol;
    public TableColumn userIdCol;
    public TableColumn contactIdCol;
    public TableView appointmentDataTable;
    public RadioButton weekRadButton;
    public RadioButton monthRadButton;
    public Button reportButton;

    /**
     *Populates the table with appointments, defaults to appointments in the current month
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("custID"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appointmentDataTable.setItems(DBAppointments.getByMonth());

    }

    /**
     * Switches between appointment form and customer form
     */
    public void onSelect(ActionEvent actionEvent) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/View/customerForm.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Customer Form");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Moves to the add appointment form when selected
     */
    public void onAdd(ActionEvent actionEvent) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/View/addAppointmentForm.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Add Appointment Form");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Moves to the update appointment screen when selected. Saves selected appointment to variable in order to pass the information to the update screen.
     *
     * throws error message if button is pushed but no appointment is selected
     */
    public void onUpdate(ActionEvent actionEvent) {
        try{
            Appointments selectedAppointment = (Appointments)appointmentDataTable.getSelectionModel().getSelectedItem();
            updateAppointmentForm.updateAppointment(selectedAppointment);

            if(selectedAppointment == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please select an Appointment");
                alert.show();
            }
            else{
                Parent root = FXMLLoader.load(getClass().getResource("/View/updateAppointmentForm.fxml"));
                Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Update Appointment Form");
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * method for deleting selected appointment. Throws error message if no appointment is selected when button is pressed
     */
    public void onDelete(ActionEvent actionEvent) {
        try{
            Appointments appointment = (Appointments) appointmentDataTable.getSelectionModel().getSelectedItem();
            if(appointment == null){
                throw new Exception();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK){
                    String sql = "DELETE FROM appointments where Appointment_ID LIKE " + appointment.getId();
                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
                ps.executeUpdate();
                if (weekRadButton.isSelected()) {
                    appointmentDataTable.setItems(DBAppointments.getByWeek());
                } else {
                    appointmentDataTable.setItems(DBAppointments.getByMonth());
                 }
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select an appointment to delete!");
            alert.showAndWait();
        }
    }

    /**
     * Method to populate appointment table view by current week when the week radio button is selected
     */
    public void byWeek(ActionEvent actionEvent) {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("custID"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appointmentDataTable.setItems(DBAppointments.getByWeek());
    }
    /**
     * Method to populate appointment table view by current month when the month radio button is selected.
     *
     * Month is elected by default
     */
    public void byMonth(ActionEvent actionEvent) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("custID"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appointmentDataTable.setItems(DBAppointments.getByMonth());
    }

    /**
     * Method for moving to the reports page
     */
    public void onReport(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/reports.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Reports Form");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
    }
}
