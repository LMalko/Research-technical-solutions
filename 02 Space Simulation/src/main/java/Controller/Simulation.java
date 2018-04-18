package Controller;

import Model.FileContent;
import Model.Item;
import Model.Rocket;
import View.View;

import java.security.InvalidParameterException;
import java.util.ArrayList;

class Simulation {

        private ArrayList<Item> phaseOneContentCollection = new ArrayList<>();
        private ArrayList<Item> phaseTwoContentCollection = new ArrayList<>();
        private View view = new View();

        Simulation(String phaseOneSource, String phaseTwoSource){
                ArrayList<String> phaseOneContent = new FileContent(phaseOneSource).getDataToCollection();
                ArrayList<String> phaseTwoContent = new FileContent(phaseTwoSource).getDataToCollection();

                loadItemsFromString(phaseOneContent, phaseOneContentCollection);
                loadItemsFromString(phaseTwoContent, phaseTwoContentCollection);
        }

        private void loadItemsFromString(ArrayList<String> source, ArrayList<Item> contentCollection){
                for (String aSource : source) {
                        String itemName = aSource.split("=")[0];
                        String itemWeight = aSource.split("=")[1];
                        contentCollection.add(new Item(itemName, Integer.valueOf(itemWeight)));
                }
        }

        public ArrayList<Rocket> loadRockets(int phase, Rocket rocket) throws CloneNotSupportedException {
                if(phase != 1 && phase != 2){ throw new InvalidParameterException("Only phase 1 or 2 allowed."); };

                int weightOfItems;
                if(phase == 1){weightOfItems = phaseOneContentCollection.stream().mapToInt(Item::getWeight).sum();}
                else{weightOfItems = phaseTwoContentCollection.stream().mapToInt(Item::getWeight).sum();}

                int rocketsCargoLoad = rocket.getMaxWeightWithCargoInTonnes();
                int numberOfFullRockets= weightOfItems / rocketsCargoLoad;

                ArrayList<Rocket> rocketList = new ArrayList<>();
                for(int i = 0; i < numberOfFullRockets; i++){ rocketList.add(rocket.clone()); }
                int loadModulo = weightOfItems % rocketsCargoLoad;
                if(loadModulo != 0) {
                        Rocket temp = rocket.clone();
                        temp.setCargoCarried(loadModulo);
                        rocketList.add(temp);
                }
                return rocketList;
        }

        public double runSimulation(ArrayList<Rocket> loadedRockets) throws CloneNotSupportedException {
                double totalCosts = 0;
                int counter = 0;
                for (Rocket rocket : loadedRockets) {
                        boolean isLaunch = rocket.launch();
                        if (!isLaunch) {
                                loadedRockets.add(rocket.clone());
                                view.print("Rocket %s nr. %f exploded during launch, prepare another one.", rocket.getClass(), counter);
                        } else {
                                boolean isLand = rocket.land();
                                if (!isLand) {
                                        loadedRockets.add(rocket.clone());
                                        view.print("Rocket %s nr. %f crash landed, prepare another one.", rocket.getClass(), counter);
                                }
                        }
                        counter++;
                        totalCosts += rocket.getCostInMlnDollars();
                }
                view.print("%f %s rockets were necessary");
                return totalCosts;
        }
}



