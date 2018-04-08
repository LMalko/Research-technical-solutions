package Controller;

import Iterator.CharIterator;
import Iterator.WordIterator;
import Model.FileContent;
import Model.StatisticalAnalysis;
import View.View;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ApplicationController {

        private File file;
        private String filename;
        private View view = new View();

        private WordIterator wordIterator;
        private CharIterator charIterator;

        private StatisticalAnalysis analysisWord;
        private StatisticalAnalysis analysisChar;


        public ApplicationController(String filename) {
                this.filename = filename;

                FileContent fileContent = new FileContent(this.filename);
                setOutputFile();

                wordIterator = new WordIterator(fileContent);
                charIterator = new CharIterator(fileContent);

                analysisWord = new StatisticalAnalysis(wordIterator);
                analysisChar = new StatisticalAnalysis(charIterator);
        }

        private void setOutputFile(){
                // Delete previous records, send new.
                try {
                        Files.deleteIfExists(Paths.get(String.format("%s lex analysis.txt", this.filename)));
                } catch (IOException e) {
                        e.printStackTrace();
                }
                this.file = new File(String.format("%s lex analysis.txt", this.filename));
        }

        public void startAnalysis() {
                long timeStart = System.currentTimeMillis();
                String date = getDate();

                view.print("\n\nDOCUMENT %s LEXICAL ANALYSIS [%s]: \n\n", this.filename, date);
                saveRecordToFile(file, "\n\nDOCUMENT %s LEXICAL ANALYSIS [%s]: \n\n", this.filename, date);

                runAnalysis();

                long timeEnd = System.currentTimeMillis();
                long timeDelta = timeEnd - timeStart;
                double elapsedSeconds = timeDelta / 1000.0;

                view.print("\n\n\tAnalysis took %f seconds to complete.\n\tThis lexical analysis has been " +
                                  "saved to a file in the same directory.", elapsedSeconds);
                saveRecordToFile(file, "\n\n\tAnalysis took %f seconds to complete.", elapsedSeconds);

        }

        private String getDate(){
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                return dateFormat.format(date);
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

        private void runAnalysis(){
                displayAllChars();
                displayAlphaNumericChars();
                displayAllWords();
                displayDictionarySize();
                displayTop30Words();
                displayTop30WordsLongerThan4();
                displayCharsInOrder();
        }

        private void displayAllChars(){
                view.print("\t01. All character count: %d", analysisChar.getAllCount());
                saveRecordToFile(file, "\t01. All character count: %d\n", analysisChar.getAllCount());
        }

        private void displayAlphaNumericChars(){
                charIterator.restartIterator();

                view.print("\t02. Alphanumeric character count: %d", analysisChar.getAlphaNumCount());
                saveRecordToFile(file, "\t02. Alphanumeric character count: %d\n", analysisChar.getAlphaNumCount());
        }

        private void displayAllWords(){
                wordIterator.restartIterator();

                view.print("\t03. Words count: %d", analysisWord.getAlphaNumCount());
                saveRecordToFile(file,"\t03. Words count: %d\n", analysisWord.getAlphaNumCount());
        }

        private void displayDictionarySize(){
                wordIterator.restartIterator();
                int dictionarySize = analysisWord.dictionarySize();

                view.print("\t04. Author's Dictionary (distinct words count): %d", dictionarySize);
                saveRecordToFile(file, "\t04. Author's Dictionary (distinct words count): %d\n", dictionarySize);
        }

        private void displayTop30Words(){
                wordIterator.restartIterator();

                view.print("\t05. TOP30 words occuring more than once:\n");
                saveRecordToFile(file, "\t05. TOP30 words occuring more than once:\n\n");

                List<List> topWords = analysisWord.occurMoreThan(1);
                int amountOfTopWords = 0;
                try {
                        for (int i = 0; i < 30; i++) {
                                view.print("\t\t*%02d.  %s - %s times", i + 1,
                                        topWords.get(i).get(0), topWords.get(i).get(1));
                                saveRecordToFile(file, "\t\t*%02d.  %s - %s times\n", i + 1,
                                        topWords.get(i).get(0), topWords.get(i).get(1));
                                amountOfTopWords ++;
                        }
                }catch(IndexOutOfBoundsException exception){
                        view.print("\n\t\tOnly %s word(s) occur(s) more than once.",
                                String.valueOf(amountOfTopWords));
                        saveRecordToFile(file, "\n\t\tOnly %s word(s) occur(s) more than once.\n",
                                String.valueOf(amountOfTopWords));
                }
        }

        private void displayTop30WordsLongerThan4(){
                wordIterator.restartIterator();

                view.print("\n\t06. TOP30 words occuring more than once & longer than 4 letters:\n\n");
                saveRecordToFile(file, "\n\t06. TOP30 words occuring more than once & longer than 4 letters:\n");

                List<List> topWordsMoreThan4 = analysisWord.wordsLenMoreThan4();
                int amountOfTopWords = 0;
                try {
                        for (int i = 0; i < 30; i++) {
                                view.print("\t\t*%02d.  %s - %s times", i + 1,
                                        topWordsMoreThan4.get(i).get(0), topWordsMoreThan4.get(i).get(1));
                                saveRecordToFile(file, "\t\t*%02d.  %s - %s times\n", i + 1,
                                        topWordsMoreThan4.get(i).get(0), topWordsMoreThan4.get(i).get(1));
                                amountOfTopWords ++;
                        }
                }catch(IndexOutOfBoundsException exception){
                        view.print("\n\t\tOnly %s word(s) meet(s) the requirements.",
                                String.valueOf(amountOfTopWords));
                        saveRecordToFile(file, "\n\t\tOnly %s word(s) meet(s) the requirements.\n",
                                String.valueOf(amountOfTopWords));
                }
        }

        private void displayCharsInOrder(){
                charIterator.restartIterator();
                view.print("\n\t07. Letters & digits in order of number of occurence count: \n");
                saveRecordToFile(file, "\n\t07. Letters & digits in order of number of occurence count: \n");
                List<List> orderedChars = analysisChar.occurMoreThan(1);
                int rankingNUmber = 1;

                for (int i = 0; i < orderedChars.size(); i++) {
                        view.print("\t\t*%02d.  %s - %s times", rankingNUmber, orderedChars.get(i).get(0),
                                orderedChars.get(i).get(1));
                        saveRecordToFile(file, "\t\t*%02d.  %s - %s times\n", rankingNUmber++, orderedChars.get(i).get(0),
                                orderedChars.get(i).get(1));
                }
        }
}




