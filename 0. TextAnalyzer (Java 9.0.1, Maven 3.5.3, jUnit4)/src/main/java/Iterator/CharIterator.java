package Iterator;

import Interface.IterableElement;
import Model.FileContent;


public class CharIterator implements IterableElement<String> {

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
        public void restartIterator(){
                this.index = 0;
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
