package Model;

public class Appointments {

    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private String start; // use LocalDateTime
    private String end;   // use to LocalDateTime
    private int custID;
    private int userID;
    private int contactID;

    public Appointments(int id, String title, String description, String location, String type, String start, String end, int custID, int userID, int contactID){
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
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

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
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
