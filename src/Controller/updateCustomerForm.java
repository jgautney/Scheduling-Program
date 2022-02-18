package Controller;

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

public class updateCustomerForm implements Initializable {
    public TextField custIDTF;
    public TextField custNameTF;
    public TextField custAddressTF;
    public TextField custPostCodeTF;
    public TextField custPhoneTF;
    public ComboBox countryCombo;
    public ComboBox disvionCombo;
    public Button updateButton;
    public Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onCountryCombo(ActionEvent actionEvent) {
    }

    public void onDivisionCombo(ActionEvent actionEvent) {
    }

    public void onUpdate(ActionEvent actionEvent) {//placeholder text FIXME
        try {
            System.out.println("Customer Updated!");
            Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Customers");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onCancel(ActionEvent actionEvent) {//placeholder text FIXME
        try {
            System.out.println("Customer Updated cancelled!");
            Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
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
