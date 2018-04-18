package View;

import java.util.concurrent.TimeUnit;

public class View {

        public void print(String text, Object ... args){
                System.out.println(String.format(text, args));
        }

        public void print(String text){
                System.out.println(text);
        }

        private void print(char character){
                System.out.print(character);
        }

        public void clearScreen() {
                System.out.print("\033[H\033[2J");
                System.out.flush();
        }

        public void delayPrint(String text){
                for(char character: text.toCharArray()) {
                        print(character);
                        try {
                                long delay = 2;
                                TimeUnit.MILLISECONDS.sleep(delay);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
        }
}
