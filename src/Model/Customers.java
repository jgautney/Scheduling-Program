package Model;

public class Customers {

    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private int divisionID;

    public Customers(int id, String name, String address, String postalCode, String phoneNumber, int divisionID){
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this. phoneNumber = phoneNumber;
        this.divisionID = divisionID;
    }
    @Override
    public String toString(){
        return(Integer.toString(id));
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getPostalCode(){
        return postalCode;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public int getDivisionID(){
        return divisionID;
    }



}
