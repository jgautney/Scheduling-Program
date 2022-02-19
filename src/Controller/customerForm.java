package Controller;

import DBAccess.DBCustomers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class customerForm implements Initializable {

    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public Button apptButton;

    public TableColumn idCol;
    public TableColumn nameCol;
    public TableColumn addressCol;
    public TableColumn postalCol;
    public TableColumn phoneCol;
    public TableColumn divisionCol;
    public TableView customerDataTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        customerDataTable.setItems(DBCustomers.getAllCustomers());

    }


    public void onAdd(ActionEvent actionEvent) {
        try {
            System.out.println("To add screen!"); //place holder text FIXME
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

    public void onUpdate(ActionEvent actionEvent) {
        try {
            System.out.println("To update Screen!"); //place holder text FIXME
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

    public void onDelete(ActionEvent actionEvent) {
        System.out.println("Delete Customer!"); //place holder text FIXME
    }

    public void onSelect(ActionEvent actionEvent) {
        try{
            System.out.println("To appointment screen!!"); //place holder text FIXME
            Parent root = FXMLLoader.load(getClass().getResource("/view/appointmentForm.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("Appointment Form");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
