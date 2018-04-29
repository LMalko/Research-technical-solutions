package Model;

public class Analyst extends User {

        public Analyst(String name, String surname, String password) {
                super(name, surname, password, "analyst");
                this.login = String.format("%s.%s@analyst.shop.com", name.toLowerCase(), surname.toLowerCase());
        }

        public Analyst(int id, String name, String surname, String password) {
                super(id, name, surname, password, "analyst");
        }

}


