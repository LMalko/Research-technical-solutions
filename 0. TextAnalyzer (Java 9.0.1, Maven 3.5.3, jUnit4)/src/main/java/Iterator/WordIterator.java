package Iterator;

import java.util.Iterator;

public class WordIterator implements Iterator<String>{

        private int index;
        private String textContent;
        private String[] words;

        public WordIterator(String textContent){
                this.index = 0;
                this.textContent = textContent;
                this.stringToWordsArray(this.textContent);
        }

        private void stringToWordsArray(String textContent){
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
