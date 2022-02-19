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

public class appointmentForm implements Initializable {
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
        try{
            System.out.println("To Appointment Add Screen!"); //placeholder FIXME
            Parent root = FXMLLoader.load(getClass().getResource("/View/addAppointmentForm.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Add Appointment Form");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onUpdate(ActionEvent actionEvent) {
        try{
            System.out.println("To Appointment Update Form!!"); //placeHolder FIXME
            Parent root = FXMLLoader.load(getClass().getResource("/View/updateAppointmentForm.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Update Appointment Form");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onDelete(ActionEvent actionEvent) {
        System.out.println("Appointment Deleted!"); //placeholder FIXME
    }
}
