package DBAccess;

import JDBC.JDBC;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBUsers {

    public static ObservableList<Users> getAllUsers(){

        ObservableList<Users> userList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from users";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("User_ID");
                String name = rs.getString("User_Name");
                String password = rs.getString("Password");

                Users u  = new Users(id, name, password);
                userList.add(u);
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return userList;
    }
}
