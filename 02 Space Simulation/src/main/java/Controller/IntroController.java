package Controller;

import Model.FileContent;

import java.util.concurrent.TimeUnit;

public class IntroController {

        private FileContent fileContent;
        private String data;
        private long delay = 7;

        public IntroController(String fileName){
                this.fileContent = new FileContent(fileName);
                this.data = fileContent.getData();
        }

        public void displayFileContent(){
                for(char character: this.data.toCharArray()) {
                        System.out.print(character);
                        try {
                                TimeUnit.MILLISECONDS.sleep(delay);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
        }
}
