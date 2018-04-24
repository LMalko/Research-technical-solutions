package View;

import java.io.Console;
import java.util.Scanner;

public class LoginView extends View{

        private Scanner reader = new Scanner(System.in);

        public String getPassword(){
                Console console = System.console();
                char passwordArray[] = console.readPassword("Enter password: ");
                String password = String.valueOf(passwordArray);
                return password;
        }

        public String getLogin(){
                System.out.println("Enter login: ");
                String login = reader.nextLine();
                return login;
        }

        public void closeScanner(){
                reader.close();
        }
}
