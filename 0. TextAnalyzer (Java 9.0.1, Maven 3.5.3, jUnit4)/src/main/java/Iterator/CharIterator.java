package Iterator;

import java.util.Iterator;

public class CharIterator implements Iterator{

        private int index;
        private String textContent;
        private char[] chars;

        public CharIterator(String textContent) {
                this.index = 0;
                this.textContent = textContent;
                this.stringToCharsArray(this.textContent);
        }

        private void stringToCharsArray(String textContent) {
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
