package Iterator;

import Model.FileContent;
import java.util.ArrayList;
import java.util.Iterator;

public class WordIterator implements Iterator<String>{

        private int index;
        private String textContent;
        private ArrayList<String> words;

        public WordIterator(String textContent){
                this.index = 0;
                this.textContent = textContent;
                this.stringToWordsArray(this.textContent);
        }

        private ArrayList<String> stringToWordsArray(String textContent){

        }

        @Override
        public boolean hasNext() {
                return false;
        }

        @Override
        public String next() {
                return "test";

        }
}
