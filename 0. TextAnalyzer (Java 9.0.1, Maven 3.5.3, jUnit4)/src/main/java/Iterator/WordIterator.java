package Iterator;

import Model.FileContent;

import java.util.Iterator;

public class WordIterator implements Iterator{

        private FileContent fileContent;

        public WordIterator(FileContent fileContent) {
                this.fileContent = fileContent;
        }


        @Override
        public boolean hasNext() {
                return false;
        }

        @Override
        public Object next() {
                return null;
        }
}
