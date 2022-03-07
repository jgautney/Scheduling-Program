package Model;


import java.time.LocalDateTime;
import java.time.ZoneId;


public class Appointments {

    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int custID;
    private int userID;
    private int contactID;

    public Appointments(int id, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int custID, int userID, int contactID){
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

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
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

    @Override
    public String toString(){
        return("Time: " + start);
    }

    public LocalDateTime getConvertedStart(LocalDateTime localDateTime){

        LocalDateTime startTime = start;

        ZoneId startZoneId = ZoneId.systemDefault();
        ZoneId startZone = ZoneId.of("America/New_York");

        return startTime.atZone(startZoneId).withZoneSameInstant(startZone).toLocalDateTime();
    }

    public LocalDateTime getConvertedEnd(LocalDateTime localdateTime){

        LocalDateTime endTime = end;

        ZoneId endZoneId = ZoneId.systemDefault();
        ZoneId endZone = ZoneId.of("America/New_York");

        return endTime.atZone(endZoneId).withZoneSameInstant(endZone).toLocalDateTime();
    }
}
