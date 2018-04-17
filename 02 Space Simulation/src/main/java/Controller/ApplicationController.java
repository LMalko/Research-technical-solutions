package Controller;

import View.*;

public class ApplicationController {

        private IntroController introController;
        private View view = new View();
        private Simulation simulation;

        public ApplicationController(String filename){
                introController = new IntroController(filename);
        }

        public void startApp(){
                view.clearScreen();
                introController.displayFileContent();
                simulation = new Simulation("resources/Phase1.txt", "resources/Phase2.txt");
        }
}
