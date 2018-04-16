package Controller;

import Model.FileContent;
import View.View;
import java.util.concurrent.TimeUnit;

public class IntroController {

        private String data;
        private View view = new View();

        IntroController(String fileName){
                FileContent fileContent = new FileContent(fileName);
                this.data = fileContent.getData();
        }

        public void displayFileContent(){
                for(char character: this.data.toCharArray()) {
                        view.print(character);
                        try {
                                long delay = 7;
                                TimeUnit.MILLISECONDS.sleep(delay);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
        }
}
