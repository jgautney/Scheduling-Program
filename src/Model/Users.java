package Model;

/**
 * Class for Users
 */
public class Users {
    private int id;
    private String name;
    private String password;

    /**
     * Constructor User object
     */
        public Users(int id, String name, String password){
            this.id = id;
            this.name = name;
            this.password = password;
        }

    /**
     * Overrides toString method to display id properly
     * @return returns User ID as a string to display in combo box properly
     */
    @Override
        public String toString(){
            return (Integer.toString(id));
        }

    /**
     * Getter for User ID
     * @return returns users ID
     */
    public int getId(){
            return id;
        }

    /**
     * Getter for User name
     * @return returns User name
     */
    public String getName(){
            return name;
        }

}
