package Model;

/**
 * Class for Countries
 */
public class Country {

    private int id;
    private String name;

    /**
     * Constructor for Country object
     */
    public Country(int id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * Overrides toString method to display Country name properly
     * @return returns country name
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     * Getter for Country ID
     * @return returns Country ID
     */
    public int getId(){
        return id;
    }

    /**
     * Getter for Country Name
     * @return returns Country Name
     */
    public String getName() {
        return name;
    }
}
