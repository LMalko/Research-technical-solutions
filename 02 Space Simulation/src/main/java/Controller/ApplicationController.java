package Controller;

import View.View;

public class ApplicationController {

        private IntroController introController;
        private View view = new View();
        private Simulation simulation;

        public ApplicationController(String filename){
                introController = new IntroController(filename);
        }

        public void startApp() throws CloneNotSupportedException {
                view.clearScreen();
                introController.displayFileContent();
                simulation = new Simulation("resources/Phase1.txt", "resources/Phase2.txt");
//                U4Prototype temp = new U4Prototype();
//                ArrayList<Rocket> uolist = simulation.loadRockets(2, temp);
//                for(int i = 0; i < uolist.size(); i++){
//                        System.out.println(uolist.get(i).getCargoCarried());
//                }
        }
}