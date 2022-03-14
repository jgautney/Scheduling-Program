package Model;

/**
 * class for first level divisions
 */
public class firstLevelDivision {

    private String name;
    private int id;

    /**
     * Constructor for firstLevelDivision object
     */
    public firstLevelDivision(int id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for first level divisions ID
     * @return returns division ID
     */
    public int getID(){
        return id;
    }

    /**
     * Overrides toString method to display name properly
     * @return returns name of the first level division
     */
    @Override
    public String toString(){
        return name;
    }

}
