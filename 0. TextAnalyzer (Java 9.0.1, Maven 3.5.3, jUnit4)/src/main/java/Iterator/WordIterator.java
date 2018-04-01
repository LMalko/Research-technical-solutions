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

        @Override
        public boolean hasNext() {
                return index < words.length;
        }

        @Override
        public String next() {
                try {
                        String temp = words[index++];
                        if (!temp.isEmpty()) {
                                return String.valueOf(temp);
                        } else {
                                return next();
                        }
                }catch(ArrayIndexOutOfBoundsException exception){
                        return null;
                }

        }
}
