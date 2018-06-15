package Controller;

import Model.FileContent;
import Enum.RegexLib;
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
                        String itemName = aSource.split(RegexLib.EQUALS_SIGN.getRegex())[0];
                        String itemWeight = aSource.split(RegexLib.EQUALS_SIGN.getRegex())[1];
                        contentCollection.add(new Item(itemName, Integer.valueOf(itemWeight)));
                }
        }

        public ArrayList<Rocket> loadRockets(int phase, Rocket rocket) throws CloneNotSupportedException {
                if(phase != 1 && phase != 2){ throw new InvalidParameterException("Only phase 1 or 2 allowed."); }

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

        public double getRocketsRequired(ArrayList<Rocket> loadedRockets) throws CloneNotSupportedException {
                double rocketCounter = 1;
                String rocketType = loadedRockets.get(0).getClass().toString().split(RegexLib.DOT.getRegex())[1];
                for (int i = 0; i < loadedRockets.size(); i++, rocketCounter++) {
                        Rocket rocket = loadedRockets.get(i);
                        boolean isLaunch = rocket.launch();
                        if (!isLaunch) {
                                loadedRockets.add(rocket.clone());
                                view.print("Rocket %s nr. %f exploded during launch, prepare another one.", rocketType, rocketCounter);
                        } else {
                                boolean isLand = rocket.land();
                                if (!isLand) {
                                        loadedRockets.add(rocket.clone());
                                        view.print("Rocket %s nr. %f crash landed, prepare another one.", rocketType, rocketCounter);
                                }
                        }
                }
                view.print("%f %s rockets were necessary", rocketCounter, rocketType);
                return rocketCounter;
        }
}



