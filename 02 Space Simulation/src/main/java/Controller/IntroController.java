package Controller;

import Model.FileContent;
import View.View;

public class IntroController {

        private String data;
        private View view = new View();

        IntroController(String fileName){
                FileContent fileContent = new FileContent(fileName);
                this.data = fileContent.getDataToString();

        }

        public void displayData(){
                view.delayPrint(this.data, 2);
        }
}
