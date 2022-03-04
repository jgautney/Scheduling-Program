package DBAccess;

import JDBC.JDBC;
import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.*;



public class DBAppointments {

    public static ObservableList<Appointments> getByMonth(){

        ObservableList<Appointments> monthList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from appointments WHERE MONTH(start) = MONTH(NOW())";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalTime start = rs.getTimestamp("Start").toLocalDateTime().toLocalTime();
                LocalTime end = rs.getTimestamp("End").toLocalDateTime().toLocalTime();
                LocalDate date = rs.getDate("Start").toLocalDate();
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointments a = new Appointments(id, title, description, location, type, start, end, date, custID, userID, contactID);
                monthList.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return monthList;
    }

    public static ObservableList<Appointments> getByWeek(){
        ObservableList<Appointments> weekList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM appointments WHERE WEEK(start) = WEEK(NOW())";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalTime start = rs.getTimestamp("Start").toLocalDateTime().toLocalTime();
                LocalTime end = rs.getTimestamp("End").toLocalDateTime().toLocalTime();
                LocalDate date = rs.getDate("Start").toLocalDate();
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointments a = new Appointments(id, title, description, location, type, start, end, date, custID, userID, contactID);
                weekList.add(a);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return weekList;
    }

    public static ObservableList<Appointments> getAllAppointments(int customerID){
        ObservableList<Appointments> apptList =FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM appointments WHERE Customer_ID LIKE " + customerID;

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalTime start = rs.getTimestamp("Start").toLocalDateTime().toLocalTime();
                LocalTime end = rs.getTimestamp("End").toLocalDateTime().toLocalTime();
                LocalDate date = rs.getDate("Start").toLocalDate();
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointments a = new Appointments(id, title, description, location, type, start, end, date, custID, userID, contactID);
                apptList.add(a);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return apptList;
    }


}
