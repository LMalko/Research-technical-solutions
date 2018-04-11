import Controller.ApplicationController;

public class Main {

        public static void main(String[] args) {

                int fileNamePosition = 0;
                String filename = args[fileNamePosition];

                ApplicationController appController = new ApplicationController(filename);

                appController.startAnalysis();
        }
}
