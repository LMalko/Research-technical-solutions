package Controller;

import Iterator.CharIterator;
import Model.FileContent;
import Iterator.WordIterator;
import Model.StatisticalAnalysis;

public class ApplicationController {

        private String filename;
        private FileContent fileContent;

        public ApplicationController(String filename) {
                this.filename = filename;
                fileContent = new FileContent(filename);
        }

        public void results(){
                System.out.println(String.format("\n\nDOCUMENT %s LEXICAL ANALYSIS: \n\n", this.filename));

                StatisticalAnalysis analysisChar = new StatisticalAnalysis(new CharIterator(fileContent.textContent()));
                System.out.println(String.format("      Alphanumeric character count: %d", analysisChar.getCount()));

                StatisticalAnalysis analysisWord = new StatisticalAnalysis(new WordIterator(fileContent.textContent()));
                System.out.println(String.format("      Words count: %d", analysisWord.getCount()));


                analysisWord = new StatisticalAnalysis(new WordIterator(fileContent.textContent()));
                System.out.println(String.format("      Author's Dictionary (distinct words count): %d",analysisWord.dictionarySize()));
        }

}
