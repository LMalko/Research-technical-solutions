package Controller;

import Model.FileContent;
import View.View;

public class IntroController {

        IntroController(String fileName){
                FileContent fileContent = new FileContent(fileName);
                String data = fileContent.getDataToString();
                View view = new View();
                view.delayPrint(data);
        }
}
