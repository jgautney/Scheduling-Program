package Controller;

import DBAccess.DBCountries;
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

/**
 * Controller for update customer form
 */
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

    /**
     * Populates the text fields and combo boxes with data carried over the selected customer
     *
     * uses an array that should always contain one element in order to set the Country and
     * Division combo boxes appropriately, while only displaying divisions based on the country selected
     */
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

    /**
     * sets items of division combo box depending on country selected
     */
    public void onCountryCombo(ActionEvent actionEvent) {
        divisionCombo.setValue(null);
        divisionCombo.setItems(getAssociatedDivisions());
    }

    /**
     * Updates the customer in the customers table of the database with the values added/changed
     */
    public void onUpdate(ActionEvent actionEvent) {
        try {
             String sql = "UPDATE customers SET Customer_Name='" + custNameTF.getText() + "', Address='" + custAddressTF.getText()
                     + "', Postal_Code='" + custPostCodeTF.getText() + "', Phone='" + custPhoneTF.getText() + "', Division_ID='"
                     + divisionCombo.getValue().getID() + "' WHERE Customer_ID=" + custIDTF.getText();

             PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
             ps.executeUpdate();


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

    /**
     * cancels updating customer and returns to the previous screen
     */
    public void onCancel(ActionEvent actionEvent) {
        try {
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

    /**
     * method used to carry customer data over from the customer table view in the customer form controller
     * @param sc is the selected customer
     */
    public static void updateCustomer(Customers sc) {
        selectedCustomer = sc;
    }

    /**
     * Method for getting the division name and ID from the selected customer
     * @return returns observable list of division Names
     */
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

    /**
     * Method uses the selected customer division ID to get the country that contains that division
     *
     * @return returns an observable list of the country associated with the division ID,
     * so it can be set to the country combo box
     */
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

    /**
     * Gets all divisions associated with the selected country.  Only divisions associated with that country will de displayed.
     * For example: if US is selected only US states will be displayed
     *
     * @return returns an observable list of the associated divisions
     */
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


