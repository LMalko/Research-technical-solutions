import Controller.ApplicationController;

public class Main {

        public static void main(String[] args) {

                String filename = args[0];
                System.out.println(filename);
                ApplicationController appController = new ApplicationController(filename);
                appController.print();
        }
}
