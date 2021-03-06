package Iterator;

import Model.FileContent;

import java.util.Iterator;


public class CharIterator implements Iterator<String> {

        private int index;
        private char[] chars;

        public CharIterator(FileContent filecontent) {
                this.index = 0;
                this.stringToCharsCollection(filecontent.getData());
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
                char temp = chars[index++];
                return String.valueOf(temp);
                     
        }
}
