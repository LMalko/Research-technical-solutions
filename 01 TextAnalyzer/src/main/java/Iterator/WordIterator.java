package Iterator;

import Enum.RegexLib;
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
                String regexNonAlphaNumeric = RegexLib.NOT_ALPHA_NUMERIC_OR_SPACE.getRegex();
                String regexSpaces = RegexLib.SPACES.getRegex();
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
