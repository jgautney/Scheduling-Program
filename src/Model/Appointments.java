package Model;

import java.sql.Date;
import java.sql.Time;


public class Appointments {

    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private Time start;
    private Time end;
    private Date date;
    private int custID;
    private int userID;
    private int contactID;

    public Appointments(int id, String title, String description, String location, String type, Time start, Time end, Date date, int custID, int userID, int contactID){
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.date = date;
        this.custID = custID;
        this.userID = userID;
        this.contactID = contactID;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getLocation(){
        return location;
    }

    public String getType() {
        return type;
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }

    public Date getDate(){
        return date;
    }

    public int getCustID() {
        return custID;
    }

    public int getUserID() {
        return userID;
    }

    public int getContactID() {
        return contactID;
    }
}
