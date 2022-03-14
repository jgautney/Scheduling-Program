package Controller;

import JDBC.JDBC;
import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import Main.CountCustomerInterface;
import Model.Contacts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ResourceBundle;

/**
 * Controller for reports form
 */
public class Reports implements Initializable {
    public ComboBox contactCombo;
    public ComboBox monthCombo;
    public ComboBox typeCombo;

    public Label monthtypeLabel;
    public Label custNumberLabel;
    public Button backButton;

    public TableColumn apptIDCol;
    public TableColumn titleCol;
    public TableColumn DescriptCol;
    public TableColumn typeCol;
    public TableColumn startCol;
    public TableColumn endCol;
    public TableColumn custIDCol;
    public TableView contactDataTable;

    /**
     * Sets items for the contact appointment schedule table view depending on which contact ID is selected in combo box
     *
     * a lambda expression is used to count total number of customers and reduce the amount of code and methods needed
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contactCombo.setItems(DBContacts.getAllContacts());
        populateMonthComboBox();

        CountCustomerInterface number = () ->{
            int counter = 0;
            for(int i = 0; i < DBCustomers.getAllCustomers().size(); i++){
                counter += 1;
            }
            return counter;
        }; // lambda for counting total number of customers

        custNumberLabel.setText(String.valueOf(number.countCustomer()));

        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        DescriptCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIDCol.setCellValueFactory(new PropertyValueFactory<>("custID"));
        contactDataTable.setItems(null);

    }

    /**
     * Gets the appointments for the associated contact that is chosen in the combo box
     */
    public void onContactSelect(ActionEvent actionEvent) {
        contactDataTable.setItems(DBAppointments.getContactAppointments((Contacts) contactCombo.getSelectionModel().getSelectedItem()));
    }

    /**
     * sets the items in the type combo box once a month is selected
     * @param actionEvent
     */
    public void onMonth(ActionEvent actionEvent) {
        typeCombo.setItems(DBAppointments.getAppointmentTypes());
    }

    /**
     * Once a type is selected, sets text in monthTypeLabel appropriately
     * @param actionEvent
     */
    public void onType(ActionEvent actionEvent) {
        monthtypeLabel.setText(getNumAppointments());

    }

    /**
     * returns to the previous screen
     */
    public void onBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentForm.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Appointment Form");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
    }

    /**
     * Populates the month combo box with all months of the year
     */
    public void populateMonthComboBox(){
        try{
            ObservableList<Month> months = FXCollections.observableArrayList();
            months.add(Month.JANUARY);
            months.add(Month.FEBRUARY);
            months.add(Month.MARCH);
            months.add(Month.APRIL);
            months.add(Month.MAY);
            months.add(Month.JUNE);
            months.add(Month.JULY);
            months.add(Month.AUGUST);
            months.add(Month.SEPTEMBER);
            months.add(Month.OCTOBER);
            months.add(Month.NOVEMBER);
            months.add(Month.DECEMBER);

            monthCombo.setItems(months);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all appointment that meets the criteria set by the month and type combo boxes
     *
     * @return returns a string of the value count, which is the total number of appointments
     * by the type and month selected
     */
    public String getNumAppointments(){
        int count = 0;

        ObservableList<String> numTypes = FXCollections.observableArrayList();
         try{

             String sql = "SELECT Type from appointments WHERE MONTHNAME(Start) = '" + monthCombo.getValue() + "' AND Type = '" + typeCombo.getValue() + "'";

             PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery();

             while(rs.next()){
                 String type = rs.getString("Type");

                 numTypes.add(type);
             }
             for(int i = 0; i < numTypes.size(); i++){
                 count+=1;
             }
         }
         catch (SQLException throwables){
             throwables.printStackTrace();
         }
        return String.valueOf(count);
    }
}
