package Model;

public class Cashier extends User{

        public Cashier(String name, String surname, String password) {
                super(name, surname, password, "cashier");
        }

        public Cashier(int id, String name, String surname, String password) {
                super(name, surname, password, "cashier");
        }

}
