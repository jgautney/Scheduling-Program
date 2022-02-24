package Controller;

import DBAccess.DBCountries;
import DBAccess.DBFirstLevelDivision;
import JDBC.JDBC;
import Model.Country;
import Model.Customers;
import Model.firstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class updateCustomerForm implements Initializable {
    public TextField custIDTF;
    public TextField custNameTF;
    public TextField custAddressTF;
    public TextField custPostCodeTF;
    public TextField custPhoneTF;
    public ComboBox<Country> countryCombo;
    public ComboBox<firstLevelDivision> divisionCombo;
    public Button updateButton;
    public Button cancelButton;

    private static Customers selectedCustomer = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            int divID = selectedCustomer.getDivisionID();
            for (firstLevelDivision f : getDivision()) {
                if(divID == f.getID()){
                    divisionCombo.setValue(f);
                }
            }
            custIDTF.setText(String.valueOf(selectedCustomer.getId()));
            custNameTF.setText(selectedCustomer.getName());
            custAddressTF.setText(selectedCustomer.getAddress());
            custPostCodeTF.setText(selectedCustomer.getPostalCode());
            custPhoneTF.setText(selectedCustomer.getPhoneNumber());
            countryCombo.setItems(DBCountries.getAllCountries());

            countryCombo.setValue(getAssociatedCountry().get(0)); //uses index[0] of getAssociatedCountry as it should only contain 1 element

            System.out.println(getAssociatedCountry());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onCountryCombo(ActionEvent actionEvent) {
        divisionCombo.setValue(null);
        divisionCombo.setItems(getAssociatedDivisions());
    }

    public void onDivisionCombo(ActionEvent actionEvent) {
    }

    public void onUpdate(ActionEvent actionEvent) {
        try {
            System.out.println("Customer Updated!"); //placeholder text FIXME
            Parent root = FXMLLoader.load(getClass().getResource("/View/customerForm.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Customers");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onCancel(ActionEvent actionEvent) {
        try {
            System.out.println("Customer Updated cancelled!"); //placeholder text FIXME
            Parent root = FXMLLoader.load(getClass().getResource("/View/customerForm.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Customers");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void updateCustomer(Customers sc) {
        selectedCustomer = sc;
    }

    public static ObservableList<firstLevelDivision> getDivision(){
        ObservableList<firstLevelDivision> divisionList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Division_ID, Division from first_level_divisions where Division_ID like " + selectedCustomer.getDivisionID();

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String name = rs.getString("Division");
                int id = rs.getInt("Division_ID");

                firstLevelDivision f = new firstLevelDivision(id, name);
                divisionList.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return divisionList;
    }

    public static ObservableList<Country> getAssociatedCountry(){
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Country, Country_ID from countries WHERE Country_ID " +
                    "in (Select Country_ID from first_level_divisions Where Division_ID like " + selectedCustomer.getDivisionID() + ")";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String name = rs.getString("Country");
                int id = rs.getInt("Country_ID");

                Country c = new Country(id, name);
                countryList.add(c);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countryList;
    }

    public ObservableList<firstLevelDivision> getAssociatedDivisions(){
        ObservableList<firstLevelDivision> divisionList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Division_ID, Division from first_level_divisions where Country_ID like " + countryCombo.getValue().getId();

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String name = rs.getString("Division");
                int id = rs.getInt("Division_ID");

                firstLevelDivision f = new firstLevelDivision(id, name);
                divisionList.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return divisionList;
    }
}


