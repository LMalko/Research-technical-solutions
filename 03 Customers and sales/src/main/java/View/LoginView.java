package View;

import java.io.Console;
import java.util.Scanner;

public class LoginView extends View{

        private Scanner reader = new Scanner(System.in);

        public String getPassword(){
                Console console = System.console();
                try {
                        char passwordArray[] = console.readPassword("Enter password: ");
                        return String.valueOf(passwordArray);
                }catch(NullPointerException exception){
                        System.out.println("Enter password: ");
                        return reader.nextLine();
                }
        }

        public String getLogin(){
                System.out.println("Enter login: ");
                return reader.nextLine();
        }

        public void closeScanner(){
                reader.close();
        }
}
