package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

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

    public void onSelect(ActionEvent actionEvent) {
        System.out.println("Change screen to appointments!");
    }

    public void onAdd(ActionEvent actionEvent) {
        System.out.println("To add screen!");
    }

    public void onUpdate(ActionEvent actionEvent) {
        System.out.println("To update Screen!");
    }

    public void onDelete(ActionEvent actionEvent) {
        System.out.println("Delete Customer!");
    }
}
