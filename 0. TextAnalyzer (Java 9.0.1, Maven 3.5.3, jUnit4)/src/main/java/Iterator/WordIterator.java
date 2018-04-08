package Iterator;

import Model.FileContent;

import java.util.Iterator;

public class WordIterator implements Iterator<String>{

        private int index;
        private String[] words;

        public WordIterator(FileContent fileContent){
                this.index = 0;
                this.stringToWordsCollection(fileContent.getData());
        }

        public void restartIterator(){
                this.index = 0;
        }

        private void stringToWordsCollection(String textContent){
                words = textContent.replaceAll("[^A-Za-z0-9\\s]", "").split("\\s+");
        }

        @Override
        public boolean hasNext() {
                return index < words.length;
        }

        @Override
        public String next() {
                String temp = words[index++];
                return String.valueOf(temp);
        }
}
