package Controller;

import Model.FileContent;

public class ApplicationController {

        private String filename;

        public ApplicationController(String filename) {
                this.filename = filename;
        }

        public void print(){
                FileContent fileContent = new FileContent(this.filename);
                System.out.println(fileContent.textContent());
        }

}
