package Model;

public class Cashier extends User{

        public Cashier(String name, String surname, String password) {
                super(name, surname, password, "cashier");
                this.login = String.format("%s.%s@cashier.shop.com", name.toLowerCase(), surname.toLowerCase());
        }

        public Cashier(int id, String name, String surname, String login, String password) {
                super(id, name, surname, login, password, "cashier");
        }

}
