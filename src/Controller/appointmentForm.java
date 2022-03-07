package Controller;

import JDBC.JDBC;
import DBAccess.DBAppointments;
import Model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

    public void onUpdate(ActionEvent actionEvent) {
        try{
            Appointments selectedAppointment = (Appointments)appointmentDataTable.getSelectionModel().getSelectedItem();
            updateAppointmentForm.updateAppointment(selectedAppointment);

            Parent root = FXMLLoader.load(getClass().getResource("/View/updateAppointmentForm.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Update Appointment Form");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onDelete(ActionEvent actionEvent) {
        try{
            Appointments appointment = (Appointments) appointmentDataTable.getSelectionModel().getSelectedItem();
            String sql = "DELETE FROM appointments where Appointment_ID LIKE " + appointment.getId();
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
            if(weekRadButton.isSelected()){
                appointmentDataTable.setItems(DBAppointments.getByWeek());
            }
            else{
                appointmentDataTable.setItems(DBAppointments.getByMonth());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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
}
