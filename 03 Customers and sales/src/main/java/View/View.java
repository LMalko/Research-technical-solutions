package View;

public abstract class View {

        public void print(String text){
                System.out.println(text);
        }

        public void clearScreen() {
                System.out.print("\033[H\033[2J");
                System.out.flush();
        }

}
