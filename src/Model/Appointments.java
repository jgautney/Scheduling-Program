package Model;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * Class for appointments
 */

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

    /**
     * constructor for appointment object
     *
     */
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

    /**
     * Getter for  appointment ID
     * @return returns appointment ID
     */
    public int getId(){
        return id;
    }
    /**
     * Getter for  appointment title
     * @return returns appointment title
     */
    public String getTitle(){
        return title;
    }
    /**
     * Getter for  appointment description
     * @return returns appointment description
     */
    public String getDescription(){
        return description;
    }
    /**
     * Getter for  appointment location
     * @return returns appointment location
     */
    public String getLocation(){
        return location;
    }
    /**
     * Getter for  appointment type
     * @return returns appointment type
     */
    public String getType() {
        return type;
    }
    /**
     * Getter for  appointment start
     * @return returns appointment start
     */
    public LocalDateTime getStart() {
        return start;
    }
    /**
     * Getter for  appointment end
     * @return returns appointment end
     */
    public LocalDateTime getEnd() {
        return end;
    }
    /**
     * Getter for Customer ID associated with the appointment
     * @return returns Customer ID
     */
    public int getCustID() {
        return custID;
    }
    /**
     * Getter for User ID associated with the appointment
     * @return returns User ID
     */
    public int getUserID() {
        return userID;
    }
    /**
     * Getter for Contact ID associated with the appointment
     * @return returns Contact ID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Overrides toString method to display appointment type properly
     * @return returns appointment type
     */
    @Override
    public String toString(){
        return(type);
    }

    /**
     * Converts provided localDateTime to EST localTime
     * @param localDateTime of system default
     * @return returns the LocalTime at EST TimeZone
     */
    public LocalTime getConvertedStart(LocalDateTime localDateTime){

        LocalDateTime startTime = start;

        ZoneId startZoneId = ZoneId.systemDefault();
        ZoneId startZone = ZoneId.of("America/New_York");

        return startTime.atZone(startZoneId).withZoneSameInstant(startZone).toLocalDateTime().toLocalTime();
    }

    /**
     * Converts provided localDateTime to EST localTime
     * @param localdateTime of system default
     * @return returns LocalTime at EST TimeZone
     */
    public LocalTime getConvertedEnd(LocalDateTime localdateTime){

        LocalDateTime endTime = end;

        ZoneId endZoneId = ZoneId.systemDefault();
        ZoneId endZone = ZoneId.of("America/New_York");

        return endTime.atZone(endZoneId).withZoneSameInstant(endZone).toLocalDateTime().toLocalTime();
    }
}
