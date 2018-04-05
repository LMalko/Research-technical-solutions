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

        private String filename;
        private FileContent fileContent;
        private View view = new View();
        private StatisticalAnalysis analysisWord;
        private StatisticalAnalysis analysisChar;
        private File file;

        public ApplicationController(String filename) {
                this.filename = filename;

                fileContent = new FileContent(this.filename);
                setOutputFile();

                analysisWord = new StatisticalAnalysis(new WordIterator(fileContent));
                analysisChar = new StatisticalAnalysis(new CharIterator(fileContent));
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

        private void saveRecordToFile(File file, String text, Object ... args){
                try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                        writer.write(String.format(text, args));
                        writer.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        private String getDate(){
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                return dateFormat.format(date);
        }

        public void runAnalysis() {
                long timeStart = System.currentTimeMillis();
                String date = getDate();

                view.print("\n\nDOCUMENT %s LEXICAL ANALYSIS [%s]: \n\n", this.filename, date);
                saveRecordToFile(file, "\n\nDOCUMENT %s LEXICAL ANALYSIS [%s]: \n\n", this.filename, date);

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
                view.print("\n\tThis lexical analysis has been saved to a file in the same directory.");
                saveRecordToFile(file, "\n\n\tAnalysis took %f seconds to complete.", elapsedSeconds);

        }
        private void displayAllChars(){
                analysisChar = new StatisticalAnalysis(new CharIterator(fileContent));

                int count = analysisChar.getCount();
                view.print("\t01. Alphanumeric character count: %d", count);
                saveRecordToFile(file, "\t01. Alphanumeric character count: %d\n", count);
        }

        private void displayAllWords(){
                analysisWord = new StatisticalAnalysis(new WordIterator(fileContent));

                int count = analysisWord.getCount();
                view.print("\t02. Words count: %d", count);
                saveRecordToFile(file,"\t02. Words count: %d\n", count);
        }

        private void displayDictionarySize(){
                analysisWord = new StatisticalAnalysis(new WordIterator(fileContent));
                int dictionarySize = analysisWord.dictionarySize();

                view.print("\t03. Author's Dictionary (distinct words count): %d", dictionarySize);
                saveRecordToFile(file, "\t03. Author's Dictionary (distinct words count): %d\n", dictionarySize);
        }

        private void displayTop30Words(){
                analysisWord = new StatisticalAnalysis(new WordIterator(fileContent));

                view.print("\t04. TOP30 words occuring more than once:\n");
                saveRecordToFile(file, "\t04. TOP30 words occuring more than once:\n\n");

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
                analysisWord = new StatisticalAnalysis(new WordIterator(fileContent));

                view.print("\n\t05. TOP30 words occuring more than once & longer than 4 letters:\n\n");
                saveRecordToFile(file, "\n\t05. TOP30 words occuring more than once & longer than 4 letters:\n");

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
                analysisChar = new StatisticalAnalysis(new CharIterator(fileContent));
                view.print("\n\t06. Letters & digits in order of number of occurence count: \n");
                saveRecordToFile(file, "\n\t06. Letters & digits in order of number of occurence count: \n");
                List<List> orderedChars = analysisChar.occurMoreThan(1);
                int rankingNUmber = 1;

                for (int i = 0; i < orderedChars.size(); i++) {
                        view.print("\t\t*%02d.  %s", rankingNUmber++, orderedChars.get(i).get(0),
                                           orderedChars.get(i).get(1));
                        saveRecordToFile(file, "\t\t*%02d.  %s\n", rankingNUmber, orderedChars.get(i).get(0),
                                                      orderedChars.get(i).get(1));
                }
        }
}


