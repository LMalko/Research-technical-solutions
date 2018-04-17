package Controller;

import Model.FileContent;
import Model.Item;

import java.util.ArrayList;

public class Simulation {

        private ArrayList<Item> phaseOneContentCollection = new ArrayList<>();
        private ArrayList<Item> phaseTwoContentCollection = new ArrayList<>();

        public Simulation(String phaseOneSource, String phaseTwoSource){
                String phaseOneContent = new FileContent(phaseOneSource).getData();
                String phaseTwoContent = new FileContent(phaseTwoSource).getData();

                loadItemsFromString(phaseOneContent, phaseOneContentCollection);
                loadItemsFromString(phaseTwoContent, phaseTwoContentCollection);
        }

        private void loadItemsFromString(String source, ArrayList<Item> contentCollection){
                String[] sourceCollection = source.split("\n");
                for(int i =0; i < sourceCollection.length; i++){
                        String itemName = sourceCollection[i].split("=")[0];
                        String itemWeight = sourceCollection[i].split("=")[1];
                        contentCollection.add(new Item(itemName, Integer.valueOf(itemWeight)));
                }
        }

}



