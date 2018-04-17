package Controller;

import Model.FileContent;
import Model.Item;

import java.util.ArrayList;

public class Simulation {

        private ArrayList<Item> phaseOneContentCollection = new ArrayList<>();
        private ArrayList<Item> phaseTwoContentCollection = new ArrayList<>();

        public Simulation(String phaseOneSource, String phaseTwoSource){
                ArrayList<String> phaseOneContent = new FileContent(phaseOneSource).getDataToCollection();
                ArrayList<String> phaseTwoContent = new FileContent(phaseTwoSource).getDataToCollection();

                loadItemsFromString(phaseOneContent, phaseOneContentCollection);
                loadItemsFromString(phaseTwoContent, phaseTwoContentCollection);
        }

        private void loadItemsFromString(ArrayList<String> source, ArrayList<Item> contentCollection){
                for(int i =0; i < source.size(); i++){
                        String itemName = source.get(i).split("=")[0];
                        String itemWeight = source.get(i).split("=")[1];
                        contentCollection.add(new Item(itemName, Integer.valueOf(itemWeight)));
                }
        }

        public void prini(){
                for(int i = 0; i < phaseOneContentCollection.size(); i++){
                        System.out.println(phaseOneContentCollection.get(i));
                }
                for(int i = 0; i < phaseOneContentCollection.size(); i++){
                        System.out.println(phaseOneContentCollection.get(i));
                }
        }

}



