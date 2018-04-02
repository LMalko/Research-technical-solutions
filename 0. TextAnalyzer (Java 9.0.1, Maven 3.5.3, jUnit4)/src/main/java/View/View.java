package View;

public class View {

        public void print(String text, Object ... args){
                System.out.printf(text, args);
        }

        public void print(String text){
                System.out.println(text);
        }

}
