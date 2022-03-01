package Model;

public class Users {
    private int id;
    private String name;
    private String password;

        public Users(int id, String name, String password){
            this.id = id;
            this.name = name;
            this.password = password;
        }

        @Override
        public String toString(){
            return (Integer.toString(id));
        }
        public int getId(){
            return id;
        }

        public String getName(){
            return name;
        }

}
