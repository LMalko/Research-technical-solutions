package Controller;

import Model.FileContent;
import Model.Item;

import java.util.ArrayList;

class Simulation {

        private ArrayList<Item> phaseOneContentCollection = new ArrayList<>();
        private ArrayList<Item> phaseTwoContentCollection = new ArrayList<>();

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
}



