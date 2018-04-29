package Model;

public class Analyst extends User {

        public Analyst(String name, String surname, String password) {
                super(name, surname, password, "analyst");
        }

        public Analyst(int id, String name, String surname, String password) {
                super(name, surname, password, "analyst");
        }

}
