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

    public void onContactSelect(ActionEvent actionEvent) {
        contactDataTable.setItems(DBAppointments.getContactAppointments((Contacts) contactCombo.getSelectionModel().getSelectedItem()));
    }

    public void onMonth(ActionEvent actionEvent) {
        typeCombo.setItems(DBAppointments.getAppointmentTypes());
    }

    public void onType(ActionEvent actionEvent) {
        monthtypeLabel.setText(getNumAppointments());

    }

    public void onBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentForm.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Appointment Form");
        stage.setScene(new Scene(root, 1000, 600));
        stage.show();
    }

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
        System.out.println(count);
        return String.valueOf(count);
    }
}
