package Iterator;

import Model.FileContent;

import java.util.Iterator;

public class WordIterator implements Iterator{

        private FileContent fileContent;
        private int index;

        public WordIterator(FileContent fileContent) {
                this.fileContent = fileContent;
                this.index = 0;
        }


        @Override
        public boolean hasNext() {
                return this.index < this.fileContent.getWordsCollection().size();
        }

        @Override
        public Object next() {
                return this.fileContent.getWordsCollection().get(this.index++);
        }
}
