import Controller.ApplicationController;

public class Main {

        public static void main(String[] args) {

                String filename = args[0];

                ApplicationController appController = new ApplicationController(filename);

                appController.startAnalysis();
        }
}
