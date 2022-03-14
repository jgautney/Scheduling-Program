package Model;

/**
 * Class For Customers
 */
public class Customers {

    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private int divisionID;

    /**
     * Constructor for Customer object
     */
    public Customers(int id, String name, String address, String postalCode, String phoneNumber, int divisionID){
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this. phoneNumber = phoneNumber;
        this.divisionID = divisionID;
    }

    /**
     * Overrides toString method to display Customer ID properly
     * @return returns Customer ID as a string to display in combo box properly
     */
    @Override
    public String toString(){
        return(Integer.toString(id));
    }

    /**
     *Getter for Customer ID
     * @return returns ID
     */
    public int getId(){
        return id;
    }

    /**
     * Getter for Customer name
     * @return returns name
     */
    public String getName(){
        return name;
    }
    /**
     *Getter for customer address
     * @return returns address
     */
    public String getAddress(){
        return address;
    }
    /**
     *Getter for Customer postal Code
     * @return returns Customer postal Code
     */
    public String getPostalCode(){
        return postalCode;
    }
    /**
     *Getter for Customer phone number
     * @return returns phone number
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }
    /**
     *Getter for Customer division ID
     * @return returns division ID
     */
    public int getDivisionID(){
        return divisionID;
    }
}
