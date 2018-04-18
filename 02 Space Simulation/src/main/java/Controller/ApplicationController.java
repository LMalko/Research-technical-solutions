package Controller;

import Model.*;
import View.View;

import java.util.ArrayList;

public class ApplicationController {

        private IntroController introController;
        private View view = new View();
        private Simulation simulation;

        private U0 templateU0;
        private U1 templateU1;
        private U2 templateU2;
        private U4Prototype templateU4;

        private ArrayList<Rocket> U0groupPhaseOne;
        private ArrayList<Rocket> U1groupPhaseOne;
        private ArrayList<Rocket> U2groupPhaseOne;
        private ArrayList<Rocket> U4groupPhaseOne;

        private ArrayList<Rocket> U0groupPhaseTwo;
        private ArrayList<Rocket> U1groupPhaseTwo;
        private ArrayList<Rocket> U2groupPhaseTwo;
        private ArrayList<Rocket> U4groupPhaseTwo;

        public ApplicationController(String filename){
                introController = new IntroController(filename);
        }

        public void startApp() throws CloneNotSupportedException {
                view.clearScreen();
                introController.displayFileContent();
                simulation = new Simulation("resources/Phase1.txt", "resources/Phase2.txt");

                assignRocketTypes();
                assignRocketGroupsPhaseOne();
                assignRocketGroupsPhaseTwo();

                runSimulations();
        }

        private void assignRocketTypes(){
                templateU0 = new U0();
                templateU1 = new U1();
                templateU2 = new U2();
                templateU4 = new U4Prototype();
        }

        private void assignRocketGroupsPhaseOne() throws CloneNotSupportedException {
                U0groupPhaseOne = simulation.loadRockets(1, templateU0);
                U1groupPhaseOne = simulation.loadRockets(1, templateU1);
                U2groupPhaseOne = simulation.loadRockets(1, templateU2);
                U4groupPhaseOne = simulation.loadRockets(1, templateU4);
        }

        private void assignRocketGroupsPhaseTwo() throws CloneNotSupportedException {
                U0groupPhaseTwo = simulation.loadRockets(2, templateU0);
                U1groupPhaseTwo = simulation.loadRockets(2, templateU1);
                U2groupPhaseTwo = simulation.loadRockets(2, templateU2);
                U4groupPhaseTwo = simulation.loadRockets(2, templateU4);
        }

        private void runSimulations() throws CloneNotSupportedException {
                view.print("U0 START \n\n");
                simulation.getRocketsRequired(U0groupPhaseOne);
                view.print("U1 START \n\n");
                simulation.getRocketsRequired(U1groupPhaseOne);
                view.print("U2 START \n\n");
                simulation.getRocketsRequired(U2groupPhaseOne);
                view.print("U4 START \n\n");
                simulation.getRocketsRequired(U4groupPhaseOne);
        }
}