package Model;

public class Contacts {
    private int id;
    private String name;
    private String email;

    public Contacts(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }
    @Override
    public String toString(){
        return(Integer.toString(id));
    }
}
