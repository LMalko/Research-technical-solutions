package Iterator;

import java.util.Iterator;

public class WordIterator implements Iterator<String>{

        private int index;
        private String[] words;

        public WordIterator(String textContent){
                this.index = 0;
                this.stringToWordsCollection(textContent);
        }

        private void stringToWordsCollection(String textContent){
                words = textContent.replace("[^A-Za-z0-9]", "").split("\\s+");
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
