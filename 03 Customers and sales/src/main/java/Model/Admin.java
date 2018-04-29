package Model;

public class Admin extends User {

        public Admin(String name, String surname, String password){
                super(name, surname, password, "admin");
                this.login = String.format("%s.%s@admin.shop.com", name.toLowerCase(), surname.toLowerCase());
        }

        public Admin(int id, String name, String surname, String password){
                super(id, name, surname, password, "admin");
        }
}
