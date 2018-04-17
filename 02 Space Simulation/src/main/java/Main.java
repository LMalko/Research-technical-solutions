import Controller.ApplicationController;

public class Main {

        private static final String FILENAME = "resources/Intro.txt";

        public static void main(String[] args) {

                ApplicationController appController = new ApplicationController(FILENAME);
                try {
                        appController.startApp();
                } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                }

        }
}