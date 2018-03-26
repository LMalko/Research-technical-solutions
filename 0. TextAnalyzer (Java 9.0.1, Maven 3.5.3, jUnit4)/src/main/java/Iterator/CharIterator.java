package Iterator;

import Model.FileContent;

import java.util.Iterator;

public class CharIterator implements Iterator{

        private FileContent fileContent;

        public CharIterator(FileContent fileContent) {
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
