package Model;

/**
 * Class for Contacts
 */
public class Contacts {
    private int id;
    private String name;
    private String email;

    /**
     * Constructor for Contacts object
     */
    public Contacts(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Overrides toString method to display contacts Properly
     * @return returns Contact ID as a String to display in combo box properly
     */
    @Override
    public String toString(){
        return(Integer.toString(id));
    }

    /**
     * Getter for contact ID
     * @return returns contact ID
     */
    public int getID(){
        return id;
    }
}
