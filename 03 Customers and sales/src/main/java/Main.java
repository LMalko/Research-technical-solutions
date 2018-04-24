import Controller.LoginController;

public class Main {

        public static void main(String[] args) {
                startApp();
        }

        private static void startApp(){
                LoginController loginProcedure = new LoginController();
                loginProcedure.login();
        }
}
