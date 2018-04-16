package Controller;

import View.*;

public class ApplicationController {

        private IntroController introController;
        private View view = new View();

        public ApplicationController(String filename){
                introController = new IntroController(filename);
        }

        public void startApp(){
                view.clearScreen();
                introController.displayFileContent();
        }
}
