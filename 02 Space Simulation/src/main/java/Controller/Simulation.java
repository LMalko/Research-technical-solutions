package Controller;

import Model.FileContent;
import Model.Item;

import java.util.ArrayList;

public class Simulation {

        private ArrayList<Item> phaseOneContentCollection = new ArrayList<>();
        private ArrayList<Item> phaseTwoContentCollection = new ArrayList<>();

        public Simulation(String phaseOneSource, String phaseTwoSource){
                String[] phaseOneContent = new FileContent(phaseOneSource).getDataToCollection();
                String[] phaseTwoContent = new FileContent(phaseTwoSource).getDataToCollection();

                loadItemsFromString(phaseOneContent, phaseOneContentCollection);
                loadItemsFromString(phaseTwoContent, phaseTwoContentCollection);
        }

        private void loadItemsFromString(String[] source, ArrayList<Item> contentCollection){
                for(int i =0; i < source.length; i++){
                        String itemName = source[i].split("=")[0];
                        String itemWeight = source[i].split("=")[1];
                        contentCollection.add(new Item(itemName, Integer.valueOf(itemWeight)));
                }
        }

}



