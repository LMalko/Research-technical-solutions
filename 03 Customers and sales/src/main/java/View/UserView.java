package View;

import Enum.RegExLib;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserView extends View{

        private BufferedReader br = null;
        private Scanner reader = new Scanner(System.in);

        private String importUserMenu(String filename ) {
                String userMenu = "";
                try {
                        br = new BufferedReader(new FileReader(filename));
                        StringBuilder sb = new StringBuilder();
                        String line = br.readLine();

                        while (line != null) {
                                sb.append(line);
                                sb.append(RegExLib.NEW_LINE.getRegex());
                                line = br.readLine();
                        }
                        userMenu = sb.toString();
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                } finally {
                        try {
                                br.close();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
                return userMenu;
        }

        public void print(String text, Object ... args){
                System.out.println(String.format(text, args));
        }

        private void print(char character){
                System.out.print(character);
        }

        public String getUserInput(String text){
                System.out.println(text);
                String input = reader.nextLine();
                return input;
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
