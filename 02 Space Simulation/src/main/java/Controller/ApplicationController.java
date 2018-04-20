package Controller;

import Enum.*;
import Model.*;
import View.View;

import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationController {

        private View view = new View();
        private Simulation simulation;
        private Scanner scanner = new Scanner(System.in);
        private String introFileName;

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

        private double rocketsU0required1;
        private double rocketsU1required1;
        private double rocketsU2required1;
        private double rocketsU4required1;
        private double rocketsU0required2;
        private double rocketsU1required2;
        private double rocketsU2required2;
        private double rocketsU4required2;

        public ApplicationController(String filename){
                this.introFileName = filename;
        }

        public void startApp() throws CloneNotSupportedException {
                view.clearScreen();
                IntroController introController = new IntroController(this.introFileName);
                introController.displayData();

                scanner.nextLine();
                setSimulations();
                runSimulations();
                startAgain();
                scanner.close();

        }

        private void setSimulations() throws CloneNotSupportedException {
                view.clearScreen();
                view.delayPrint("\nPreparing simulation .... \n" , 10);
                String phaseOneFilePath = "resources/Phase1.txt";
                String phaseTwoFilePath = "resources/Phase2.txt";
                simulation = new Simulation(phaseOneFilePath, phaseTwoFilePath);
                view.delayPrint("\nPreparing rockets .... \n" , 10);
                assignRocketTypes();
                view.delayPrint("\nPreparing rocket groups phase one .... \n" , 10);
                assignRocketGroupsPhaseOne();
                view.delayPrint("\nPreparing rocket groups phase two .... \n" , 10);
                assignRocketGroupsPhaseTwo();
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
                setRocketGroups();

                double U0cost = (rocketsU0required1 + rocketsU0required2) *
                        templateU0.getCostInMlnDollars() / MathLib.MILION.getExp();
                double U1cost = (rocketsU1required1 + rocketsU1required2) *
                        templateU1.getCostInMlnDollars() / MathLib.MILION.getExp();
                double U2cost = (rocketsU2required1 + rocketsU2required2) *
                        templateU2.getCostInMlnDollars() / MathLib.MILION.getExp();
                double U4cost = (rocketsU4required1 + rocketsU4required2) *
                        templateU4.getCostInMlnDollars() / MathLib.MILION.getExp();

                view.delayPrint(String.format("\n\nResults:\nFor phase one we need %f rockets U0, %f U1, %f U2 or %f U4-Prototype." +
                        "\nFor phase two we need %f rockets U0, %f U1, %f U2 or %f U4-Prototype.\n\nSo total cost when using each" +
                        "type of rocket is: \n U0 - %f bln $,\n U1 - %f bln $,\n U2 - %f bln $,\n U4-Prototype - %f bln $", rocketsU0required1,
                        rocketsU1required1, rocketsU2required1, rocketsU4required1, rocketsU0required2,
                        rocketsU1required2, rocketsU2required2, rocketsU4required2, U0cost, U1cost,
                        U2cost, U4cost),5);
        }

        private void setRocketGroups() throws CloneNotSupportedException {
                rocketsU0required1 = simulation.getRocketsRequired(U0groupPhaseOne);
                rocketsU1required1 = simulation.getRocketsRequired(U1groupPhaseOne);
                rocketsU2required1 = simulation.getRocketsRequired(U2groupPhaseOne);
                rocketsU4required1 = simulation.getRocketsRequired(U4groupPhaseOne);
                rocketsU0required2 = simulation.getRocketsRequired(U0groupPhaseTwo);
                rocketsU1required2 = simulation.getRocketsRequired(U1groupPhaseTwo);
                rocketsU2required2 = simulation.getRocketsRequired(U2groupPhaseTwo);
                rocketsU4required2 = simulation.getRocketsRequired(U4groupPhaseTwo);
        }

        private void startAgain() throws CloneNotSupportedException {
                view.print("\n\nStart again? Press y else quit.");
                String userChoice = String.valueOf(scanner.nextLine()).toLowerCase();
                if(userChoice.equals(RegexLib.Y_SIGN.getRegex())){
                        setSimulations();
                        runSimulations();
                        startAgain();
                }
        }
}
