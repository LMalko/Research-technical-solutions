package Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

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

        private void saveRecordToFile(File file, String text, Object ... args){
                try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                        writer.write(String.format(text, args));
                        writer.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public void displayResults() {
                long timeStart = System.currentTimeMillis();

                File file = new File(String.format("%s lex analysis.txt", this.filename));


                view.print("\n\nDOCUMENT %s LEXICAL ANALYSIS: \n\n", this.filename);

                saveRecordToFile(file, "\n\nDOCUMENT %s LEXICAL ANALYSIS: \n\n", this.filename);
                
                displayAllChars();
                displayAllWords();
                displayDictionarySize();
                displayTop30Words();
                displayTop30WordsLongerThan4();
                displayCharsInOrder();

                long timeEnd = System.currentTimeMillis();
                long timeDelta = timeEnd - timeStart;
                double elapsedSeconds = timeDelta / 1000.0;
                view.print("\n\n\tAnalysis took %f seconds to complete.", elapsedSeconds);                

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
                        view.print("\n\t\tOnly %s word(s) occur(s) more than once.", String.valueOf(amountOfTopWords));
                }
        }

        private void displayTop30WordsLongerThan4(){
                analysisWord = new StatisticalAnalysis(new WordIterator(fileContent.textContent()));
                view.print("\n\t05. TOP30 words occuring more than once & longer than 4 letters:\n");
                String[] topWordsMoreThan4 = analysisWord.wordsLenMoreThan4().toArray(new String[0]);
                int amountOfTopWords = 0;
                try {
                        for (int i = 0; i < 30; i++) {
                                view.print("\t\t*%02d.  %s", i + 1, topWordsMoreThan4[i]);
                                amountOfTopWords ++;
                        }
                }catch(ArrayIndexOutOfBoundsException exception){
                        view.print("\n\t\tOnly %s word(s) meet(s) the requirements.", String.valueOf(amountOfTopWords));
                }
        }

        private void displayCharsInOrder(){
                analysisChar = new StatisticalAnalysis(new CharIterator(fileContent.textContent()));
                view.print("\n\t06. Letters & digits in order of number of occurence count: \n");
                String[] orderedChars = analysisChar.occurMoreThan(1).toArray(new String[0]);
                int rankingNUmber = 1;
                for (int i = 0; i < orderedChars.length; i++) {
                        if(orderedChars[i].matches("[a-z0-9]{1} - [0-9]{1,} times")){
                                view.print("\t\t*%02d.  %s", rankingNUmber++, orderedChars[i]);
                        }
                }
        }
}


 