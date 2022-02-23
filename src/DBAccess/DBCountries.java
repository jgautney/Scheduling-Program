package DBAccess;

import JDBC.JDBC;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.*;

public class DBCountries {
    public static ObservableList<Country> getAllCountries() {

        ObservableList<Country> countryList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from countries";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Country_ID");
                String name = rs.getString("Country");

                Country c = new Country(id, name);
                countryList.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  countryList;
    }
}
