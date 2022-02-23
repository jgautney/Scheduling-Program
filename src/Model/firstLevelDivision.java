package Model;

public class firstLevelDivision {

    private String name;
    private int id;


    public firstLevelDivision(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getID(){
        return id;
    }

    @Override
    public String toString(){
        return name;
    }

}
