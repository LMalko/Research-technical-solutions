package Model;

import Interface.IterableText;

import java.util.ArrayList;
import java.util.Iterator;

public class FileContent implements IterableText{

        private String filename;

        public FileContent(String filename){
                this.filename = filename;
        }
        @Override
        public Iterator<String> charIterator() {
                return new Iterator.CharIterator(this);
        }

        @Override
        public Iterator<String> wordIterator() {
                return new Iterator.WordIterator(this);
        }

        public ArrayList<String> getWordsCollection(){

        }

        public ArrayList<String> getCharsCollection(){

        }

}
