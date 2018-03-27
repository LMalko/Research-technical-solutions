package Model;

import Interface.IterableText;
import Iterator.CharIterator;
import Iterator.WordIterator;
import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FileContent implements IterableText{

        private String fileName;


        public FileContent(String fileName){
                this.fileName = fileName;
        }
        @Override
        public Iterator<String> charIterator() {
                return new CharIterator(this.textContent());
        }

        @Override
        public Iterator<String> wordIterator() {
                return new WordIterator(this.textContent());
        }

        private String getFilename() {
                return fileName;
        }

        public String textContent(){

                String data;
                FileReader fileReader;
                BufferedReader bufferedReader;
                String line;
                String nextLine = "\n";
                StringBuilder sb = new StringBuilder();

                try {

                        fileReader = new FileReader(new File(this.getFilename()));
                        bufferedReader = new BufferedReader(fileReader);

                        while((line = bufferedReader.readLine()) != null) {

                                if(line.length() > 0) {
                                        sb.append(line.trim());
                                        sb.append(nextLine);
                                }
                        }
                        data = sb.toString();
                        if(data.length() == 0) {
                                throw new NoSuchElementException("No data.");
                        }
                } catch (IOException e) {
                        data = null;
                }

                return data;
        }

}
