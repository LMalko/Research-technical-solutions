package View;

import java.io.Console;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class View {

        private Scanner reader = new Scanner(System.in);

        public void print(String text, Object ... args){
                System.out.println(String.format(text, args));
        }

        public void print(String text){
                System.out.println(text);
        }

        private void print(char character){
                System.out.print(character);
        }

        public String getPassword(){
                Console console = System.console();
                char passwordArray[] = console.readPassword("Enter password: ");
                String password = String.valueOf(passwordArray);
                return password;
        }
        
        public void closeScanner(){
                reader.close();
        }

        public String getLogin(){
                System.out.println("Enter login: ");
                String login = reader.nextLine();
                return login;
        }

        public void clearScreen() {
                System.out.print("\033[H\033[2J");
                System.out.flush();
        }

        public void delayPrint(String text, long delay){
                for(char character: text.toCharArray()) {
                        print(character);
                        try {
                                TimeUnit.MILLISECONDS.sleep(delay);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
        }
}
