package Controller;

import Iterator.CharIterator;
import Model.FileContent;
import Iterator.WordIterator;

public class ApplicationController {

        private String filename;

        public ApplicationController(String filename) {
                this.filename = filename;
        }

        public void print(){
                FileContent fileContent = new FileContent(this.filename);
                WordIterator wordIterator = new WordIterator(fileContent.textContent());
                while(wordIterator.hasNext()){
                        System.out.println(wordIterator.next());
                }
                CharIterator charIterator = new CharIterator(fileContent.textContent());
                while(charIterator.hasNext()){
                        System.out.println(charIterator.next());
                }
        }

}
