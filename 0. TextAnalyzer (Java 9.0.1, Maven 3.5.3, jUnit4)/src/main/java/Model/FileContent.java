package Model;

import Interface.IterableText;

import java.util.Iterator;

public class FileContent implements IterableText{

        private String filename;

        public FileContent(String filename){
                return new Iterator.WordIterator(this);
        }
        @Override
        public Iterator<String> charIterator() {
                return new Iterator.CharIterator(this);
        }

        @Override
        public Iterator<String> wordIterator() {
                return null;
        }

}
