package Model;

public class Country {

    private int id;
    private String name;

    public Country(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString(){
        return(name);
    }
}
