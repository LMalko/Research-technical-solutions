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
                words = textContent.split("\\s+");
                for (int i = 0; i < words.length; i++) {
                        words[i] = words[i].replaceAll("[^\\w]", "");
                }
        }

        public String[] getWords() {
                return words;
        }

        @Override
        public boolean hasNext() {
                return index < words.length;
        }

        @Override
        public String next() {
                return words[index++];

        }
}
