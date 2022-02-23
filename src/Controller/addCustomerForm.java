package Controller;

import DBAccess.DBCountries;
import Model.Country;
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
import java.util.ResourceBundle;

public class addCustomerForm implements Initializable {

    public TextField custIDTF;
    public TextField custNameTF;
    public TextField custAddressTF;
    public TextField custPostCodeTF;
    public TextField custPhoneTF;
    public ComboBox<Country> countryCombo;
    public ComboBox disvionCombo;
    public Button addButton;
    public Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryCombo.setItems(DBCountries.getAllCountries());

    }

    public void onCountryCombo(ActionEvent actionEvent) {
    }

    public void onDivisionCombo(ActionEvent actionEvent) {
    }

    public void onAdd(ActionEvent actionEvent) {
        try {
            System.out.println("Customer Added!"); //place holder text FIXME
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
            System.out.println("Customer Add cancelled!"); //place holder text FIXME
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
}
