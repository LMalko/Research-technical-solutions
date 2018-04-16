package View;

public class View {

        public void print(String text, Object ... args){
                System.out.println(String.format(text, args));
        }

        public void print(String text){
                System.out.println(text);
        }

        public void clearScreen() {
                System.out.print("\033[H\033[2J");
                System.out.flush();
        }
}
