package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentForm implements Initializable {
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public Button customerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onSelect(ActionEvent actionEvent) {
        try{
            System.out.println("To customer screen!");//placeholder text FIXME
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
    }

    public void onUpdate(ActionEvent actionEvent) {
    }

    public void onDelete(ActionEvent actionEvent) {
    }
}
