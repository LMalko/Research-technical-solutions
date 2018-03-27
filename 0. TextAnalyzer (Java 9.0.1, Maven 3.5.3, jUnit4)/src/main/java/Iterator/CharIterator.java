package Iterator;

import Model.FileContent;

import java.util.ArrayList;
import java.util.Iterator;

public class CharIterator implements Iterator<String>{

        private int index;
        private String textContent;
        private ArrayList<String> chars;

        public CharIterator(String textContent) {
                this.index = 0;
                this.textContent = textContent;
                this.stringToCharsArray(this.textContent);
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
