package Main;

import JDBC.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Josh Gautney
 * Date: March 14, 2022
 * Class: C195 Software II: Advanced Java Concepts
 */

/**
 * Main
 */
public class Main extends Application {



    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }


    public static void main(String[] args) {

        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }

}
