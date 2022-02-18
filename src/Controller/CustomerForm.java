package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerForm implements Initializable {
    public MenuItem apptSelect;
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onSelect(ActionEvent actionEvent) {//place holder text FIXME
        System.out.println("Change screen to appointments!");
    }

    public void onAdd(ActionEvent actionEvent) {//place holder text FIXME
        try {
            System.out.println("To add screen!");
            Parent root = FXMLLoader.load(getClass().getResource("/view/addCustomerForm.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Add Customer");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onUpdate(ActionEvent actionEvent) {//place holder text FIXME
        try {
            System.out.println("To update Screen!");
            Parent root = FXMLLoader.load(getClass().getResource("/view/updateCustomerForm.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Update Customer");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onDelete(ActionEvent actionEvent) {//place holder text FIXME
        System.out.println("Delete Customer!");
    }
}
