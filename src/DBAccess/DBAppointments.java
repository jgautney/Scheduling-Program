package DBAccess;

import JDBC.JDBC;
import Model.Appointments;
import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointments a = new Appointments(id, title, description, location, type, start, end, custID, userID, contactID);
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
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointments a = new Appointments(id, title, description, location, type, start, end, custID, userID, contactID);
                weekList.add(a);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return weekList;
    }

    public static ObservableList<Appointments> getApptByID(int customerID){
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
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointments a = new Appointments(id, title, description, location, type, start, end, custID, userID, contactID);
                apptList.add(a);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return apptList;
    }

    public static ObservableList<Appointments> getAllAppointments(int userId){
        ObservableList<Appointments> apptList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM appointments WHERE User_ID LIKE " + userId;

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointments a = new Appointments(id, title, description, location, type, start, end, custID, userID, contactID);
                apptList.add(a);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return apptList;
    }

    public static ObservableList<Appointments> getContactAppointments(Contacts contact){
        ObservableList<Appointments> apptList = FXCollections.observableArrayList();

        try{
            String sql = "Select * FROM appointments WHERE Contact_ID LIKE " + contact.getID();

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointments a = new Appointments(id, title, description, location, type, start, end, custID, userID, contactID);
                apptList.add(a);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return apptList;
    }

    public static ObservableList<String> getAppointmentTypes(){
        ObservableList<String> apptList = FXCollections.observableArrayList();

        try{
            String sql = "Select Type FROM appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeQuery();

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String type = rs.getString("type");
                if(!apptList.contains(type))
                    apptList.add(type);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return apptList;
    }

}

