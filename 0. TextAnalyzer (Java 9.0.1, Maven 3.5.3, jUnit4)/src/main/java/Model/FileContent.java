package Model;

import Interface.IterableText;
import Iterator.CharIterator;
import Iterator.WordIterator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class FileContent implements IterableText{

        private String fileName;
        private BufferedReader br;


        public FileContent(String fileName){
                this.fileName = fileName;
        }
        @Override
        public Iterator<String> charIterator() {
                return new CharIterator(this);
        }

        @Override
        public Iterator<String> wordIterator() {
                return new WordIterator(this);
        }

        public String getFilename() {
                return fileName;
        }

        public BufferedReader textContent(){
                try {
                        br = new BufferedReader(new FileReader(this.fileName));
                } catch (FileNotFoundException exceptionFile) {
                        exceptionFile.printStackTrace();
                } catch (IOException exceptionIO){
                        exceptionIO.printStackTrace();
                }
                return br;
        }

}
