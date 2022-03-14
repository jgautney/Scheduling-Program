package Controller;

import DBAccess.DBCustomers;
import JDBC.JDBC;
import Model.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for the customer form
 */
public class customerForm implements Initializable {

    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public Button apptButton;

    public TableColumn idCol;
    public TableColumn nameCol;
    public TableColumn addressCol;
    public TableColumn postalCol;
    public TableColumn phoneCol;
    public TableColumn divisionCol;
    public TableView customerDataTable;

    /**
     * Populates the customer table view with all customer records
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        customerDataTable.setItems(DBCustomers.getAllCustomers());

    }

    /**
     * Moves to the add customer form
     */
    public void onAdd(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/addCustomerForm.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Add Customer");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Moves to the update customer screen. Stores selected customer in a variable to move data to the update screen.
     *
     * throws error message if no customer is selected
     */
    public void onUpdate(ActionEvent actionEvent) {
        try {

            Customers selectedCustomer = (Customers) customerDataTable.getSelectionModel().getSelectedItem();
            updateCustomerForm.updateCustomer(selectedCustomer);

            if (selectedCustomer == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please select a Customer");
                alert.show();
            } else {

                Parent root = FXMLLoader.load(getClass().getResource("/view/updateCustomerForm.fxml"));
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Update Customer");
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for deleting a customer. Displays conformation box as appointments with customer will also need
     * to be deleted according to database integrity rules.
     *
     * When user selects yes, deletes customer and all associated appointments
     *
     * Throws error message if no customer is selected when button is pressed
     */
    public void onDelete(ActionEvent actionEvent) {

        Customers customer = (Customers) customerDataTable.getSelectionModel().getSelectedItem();
        try {
            if (customer == null) {
                throw new Exception();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Deleting a customer will delete associated appointments");
                alert.setContentText("Are you sure?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    removeAppointments();
                    removeCustomers();
                    customerDataTable.setItems(DBCustomers.getAllCustomers());
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select a customer to delete!");
            alert.showAndWait();
        }
    }

    /**
     * Displays appointment form when selected
     */
    public void onSelect(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentForm.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Appointment Form");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for deleting appointments of associated customer
     */
    public void removeAppointments() {
        Customers customer = (Customers) customerDataTable.getSelectionModel().getSelectedItem();
        try {
            String sql = "DELETE FROM appointments WHERE Customer_ID LIKE " + customer.getId();
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * method for deleting customer
     */
    public void removeCustomers(){
        Customers customer = (Customers) customerDataTable.getSelectionModel().getSelectedItem();
        try{
            String sql = "DELETE FROM customers WHERE Customer_ID LIKE " + customer.getId();
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
