package DBAccess;

import JDBC.JDBC;
import Model.firstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.*;


public class DBFirstLevelDivision {
    public static ObservableList<firstLevelDivision> getAllDivisions(){
        ObservableList<firstLevelDivision> divisionList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from first_level_divisions";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String name = rs.getString("Division");
                int id = rs.getInt("Division_ID");

                firstLevelDivision f = new firstLevelDivision(id, name);
                divisionList.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return divisionList;
    }

}