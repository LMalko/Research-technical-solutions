package Model;

import Enum.RegexLib;
import Interface.IterableText;
import Iterator.CharIterator;
import Iterator.WordIterator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FileContent implements IterableText{

        private String fileName;
        private String data;


        public FileContent(String fileName){
                this.fileName = fileName;
                this.data = textContent();
        }

        public String getData() {
                return data;
        }

        @Override
        public Iterator<String> charIterator() {
                return new CharIterator(this);
        }

        @Override
        public Iterator<String> wordIterator() {
                return new WordIterator(this);
        }

        private String textContent(){

                String data;
                FileReader fileReader;
                BufferedReader bufferedReader;
                String line;
                String nextLine = RegexLib.NEW_LINE.getRegex();
                StringBuilder sb = new StringBuilder();

                try {

                        fileReader = new FileReader(new File(this.fileName));
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
