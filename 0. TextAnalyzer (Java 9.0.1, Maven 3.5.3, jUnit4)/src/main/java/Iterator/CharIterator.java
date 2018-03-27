package Iterator;

import Model.FileContent;

import java.util.Iterator;

public class CharIterator implements Iterator<String>{

        private FileContent fileContent;
        private int index;

        public CharIterator(FileContent fileContent) {
                this.fileContent = fileContent;
                this.index = 0;
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
