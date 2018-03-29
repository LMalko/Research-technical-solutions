package Controller;

import Iterator.CharIterator;
import Iterator.WordIterator;
import Model.FileContent;
import Model.StatisticalAnalysis;

public class ApplicationController {

        private String filename;
        private FileContent fileContent;

        public ApplicationController(String filename) {
                this.filename = filename;
                fileContent = new FileContent(filename);
        }

        public void displayResults() {
                System.out.println(String.format("\n\nDOCUMENT %s LEXICAL ANALYSIS: \n\n", this.filename));
                displayAllChars();
                displayAllWords();
                displayDictionarySize();
                displayTop30Words();

        }
        private void displayAllChars(){
                StatisticalAnalysis analysisChar = new StatisticalAnalysis(new CharIterator(fileContent.textContent()));
                System.out.println(String.format("\t01. Alphanumeric character count: %d", analysisChar.getCount()));
        }

        private void displayAllWords(){
                StatisticalAnalysis analysisWord = new StatisticalAnalysis(new WordIterator(fileContent.textContent()));
                System.out.println(String.format("\t02. Words count: %d", analysisWord.getCount()));
        }

        private void displayDictionarySize(){
                StatisticalAnalysis analysisWord = new StatisticalAnalysis(new WordIterator(fileContent.textContent()));
                int dictionarySize = analysisWord.dictionarySize();
                System.out.println(String.format("\t03. Author's Dictionary (distinct words count): %d", dictionarySize));
        }

        private void displayTop30Words(){
                StatisticalAnalysis analysisWord = new StatisticalAnalysis(new WordIterator(fileContent.textContent()));
                System.out.println("\t04. TOP30 words occuring more than once:\n");
                Object[] topWords = analysisWord.occurMoreThan(1).toArray();
                int amountOfTopWords = 0;
                try {
                        for (int i = 0; i < 9; i++) {
                                System.out.println(String.format("\t\t*%d.  %s", i + 1, topWords[i]));
                                amountOfTopWords ++;
                        }
                        for (int i = 9; i < 30; i++) {
                                System.out.println(String.format("\t\t*%d. %s", i + 1, topWords[i]));
                                amountOfTopWords ++;
                        }
                }catch(ArrayIndexOutOfBoundsException exception){
                        System.out.println(String.format("\n\t\tOnly %s word(s) occur more than once.", String.valueOf(amountOfTopWords)));
                }
        }
}


