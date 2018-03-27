package Iterator;

import java.util.Iterator;

public class CharIterator implements Iterator{

        private int index;
        private char[] chars;

        public CharIterator(String textContent) {
                this.index = 0;
                this.stringToCharsCollection(textContent);
        }

        private void stringToCharsCollection(String textContent) {
                chars = textContent.toCharArray();
        }

        @Override
        public boolean hasNext() {
                return index < chars.length;
        }

        @Override
        public String next() {
                try {
                        char temp = chars[index++];
                        if (Character.isLetterOrDigit(temp)) {
                                return String.valueOf(temp);
                        } else {
                                return next();
                        }
                }catch(ArrayIndexOutOfBoundsException exception){
                        return null;
                }
        }
}
