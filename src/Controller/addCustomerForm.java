package Controller;

import JDBC.JDBC;
import DBAccess.DBCountries;
import Model.Country;
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
 * Controller for add customer form
 */
public class addCustomerForm implements Initializable {

    public TextField custIDTF;
    public TextField custNameTF;
    public TextField custAddressTF;
    public TextField custPostCodeTF;
    public TextField custPhoneTF;
    public ComboBox<Country> countryCombo;
    public ComboBox<firstLevelDivision> divisionCombo;
    public Button addButton;
    public Button cancelButton;

    /**
     *initialize method.  Contains code to populate the Country combo box
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryCombo.setItems(DBCountries.getAllCountries());

    }

    /**
     * Uses getAssociatedDivisions method to populate the first level division combo box depending on what country was selected.
     * for example: if U.S. is selected in country combo box only US states will be in the first level division combo box
     */
    public void onCountryCombo(ActionEvent actionEvent) {
        try{
            divisionCombo.setItems(getAssociatedDivisions());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Adds Customer to the customers table in the database.  Contains the necessary SQL to achieve that.
     */
    public void onAdd(ActionEvent actionEvent) {
        try {
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID)" +
                    "VALUES ('" + custNameTF.getText() +"', '" + custAddressTF.getText() + "', '" + custPostCodeTF.getText() + "', '" + custPhoneTF.getText() + "', '" + divisionCombo.getValue().getID() + "')";

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
     * Cancels adding customer and returns to previous screen
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
     * Method for grabbing first level divisions depending on what is selected in the country combo box
     * @return returns observable list of associated divisions
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
