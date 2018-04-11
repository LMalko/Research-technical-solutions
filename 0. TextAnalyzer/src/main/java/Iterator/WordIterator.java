package Iterator;

import Model.FileContent;

import java.util.Iterator;

public class WordIterator implements Iterator<String> {

        private int index;
        private String[] words;

        public WordIterator(FileContent fileContent){
                this.index = 0;
                this.stringToWordsCollection(fileContent.getData());
        }

        private void stringToWordsCollection(String textContent){
                String regexNonAlphaNumeric = "[^A-Za-z0-9\\s]";
                String regexSpaces = "\\s+";
                words = textContent.replaceAll(regexNonAlphaNumeric, "").split(regexSpaces);
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
