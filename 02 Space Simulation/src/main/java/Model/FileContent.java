package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileContent {

        private String fileName;
        private ArrayList<String> data;


        public FileContent(String fileName){
                this.fileName = fileName;
                this.data = textContent();
        }

        public String getDataToString() {
                StringBuilder listString = new StringBuilder();

                for (String string : data)
                        listString.append(string).append("\n");

                return listString.toString();
        }

        public ArrayList<String> getDataToCollection(){
                return data;
        }

        private ArrayList<String> textContent(){
                ArrayList<String> collection = new ArrayList<>();
                FileReader fileReader;
                BufferedReader bufferedReader;
                String line;
                
                try {

                        fileReader = new FileReader(new File(this.fileName));
                        bufferedReader = new BufferedReader(fileReader);

                        while((line = bufferedReader.readLine()) != null) {
                                if(line.length() > 0) {
                                        collection.add(line.trim());
                                }
                        }
                } catch (IOException e) {
                        data = null;
                }
                return collection;
        }
}
