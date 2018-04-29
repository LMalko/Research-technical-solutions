package Model;

public class Admin extends User {

        public Admin(String name, String surname, String password){
                super(name, surname, password, "admin");
        }

        public Admin(int id, String name, String surname, String password){
                super(name, surname, password, "admin");
        }
}
