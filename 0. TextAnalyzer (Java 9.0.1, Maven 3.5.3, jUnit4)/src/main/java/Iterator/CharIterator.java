package Iterator;

import Model.FileContent;

import java.util.Iterator;

public class CharIterator implements Iterator{

        private FileContent fileContent;
        private int index;

        public CharIterator(FileContent fileContent) {
                this.fileContent = fileContent;
                this.index = 0;
        }

        @Override
        public boolean hasNext() {
                return this.index < fileContent.getCharsCollection().size();
        }

        @Override
        public Object next() {
                return getCharsCollection().get(this.index++);
        }
}
