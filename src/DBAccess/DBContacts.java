package DBAccess;

import JDBC.JDBC;
import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {
    public static ObservableList<Contacts> getAllContacts(){
        ObservableList<Contacts> contactList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM contacts";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contacts c = new Contacts(id, name, email);
                contactList.add(c);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactList;
    }
}
