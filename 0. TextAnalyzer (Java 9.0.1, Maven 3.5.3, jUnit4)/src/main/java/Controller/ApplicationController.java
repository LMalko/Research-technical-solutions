package Controller;

import Iterator.CharIterator;
import Iterator.WordIterator;
import Model.FileContent;
import Model.StatisticalAnalysis;
import View.View;

public class ApplicationController {

        private String filename;
        private FileContent fileContent;
        private View view = new View();
        private StatisticalAnalysis analysisWord;
        private StatisticalAnalysis analysisChar;

        public ApplicationController(String filename) {
                this.filename = filename;
                fileContent = new FileContent(filename);
                analysisWord = new StatisticalAnalysis(new WordIterator(fileContent.textContent()));
                analysisChar = new StatisticalAnalysis(new CharIterator(fileContent.textContent()));

        }

        public void displayResults() {
                view.print("\n\nDOCUMENT %s LEXICAL ANALYSIS: \n\n", this.filename);
                displayAllChars();
                displayAllWords();
                displayDictionarySize();
                displayTop30Words();
                displayTop30WordsLongerThan4();
//getcountof()
        }
        private void displayAllChars(){
                analysisChar = new StatisticalAnalysis(new CharIterator(fileContent.textContent()));
                view.print("\t01. Alphanumeric character count: %d", analysisChar.getCount());
        }

        private void displayAllWords(){
                analysisWord = new StatisticalAnalysis(new WordIterator(fileContent.textContent()));
                view.print("\t02. Words count: %d", analysisWord.getCount());
        }

        private void displayDictionarySize(){
                analysisWord = new StatisticalAnalysis(new WordIterator(fileContent.textContent()));
                int dictionarySize = analysisWord.dictionarySize();
                view.print("\t03. Author's Dictionary (distinct words count): %d", dictionarySize);
        }

        private void displayTop30Words(){
                analysisWord = new StatisticalAnalysis(new WordIterator(fileContent.textContent()));
                view.print("\t04. TOP30 words occuring more than once:\n");
                String[] topWords = analysisWord.occurMoreThan(1).toArray(new String[0]);
                int amountOfTopWords = 0;
                try {
                        for (int i = 0; i < 30; i++) {
                                view.print("\t\t*%02d.  %s", i + 1, topWords[i]);
                                amountOfTopWords ++;
                        }
                }catch(ArrayIndexOutOfBoundsException exception){
                        view.print("\n\t\tOnly %s word(s) occur more than once.", String.valueOf(amountOfTopWords));
                }
        }

        private void displayTop30WordsLongerThan4(){
                analysisWord = new StatisticalAnalysis(new WordIterator(fileContent.textContent()));
                view.print("\n\t05. TOP30 words occuring more than once & longer than 4 letters:\n");
                String[] topWordsMoreThan4 = analysisWord.wordsLenThan4().toArray(new String[0]);
                int amountOfTopWords = 0;
                try {
                        for (int i = 0; i < 30; i++) {
                                view.print("\t\t*%02d.  %s", i + 1, topWordsMoreThan4[i]);
                                amountOfTopWords ++;
                        }
                }catch(ArrayIndexOutOfBoundsException exception){
                        view.print("\n\t\tOnly %s word(s) meet the requirements.", String.valueOf(amountOfTopWords));
                }
        }
}


