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
import java.util.*;

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

        }

        private void setOutputFile() {
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

                view.print("\n\nDOCUMENT %s LEXICAL ANALYSIS [%s]: \n\nIt might take few minutes for longer texts.\n\n",
                        this.filename, date);
                saveRecordToFile(file, "\n\nDOCUMENT %s LEXICAL ANALYSIS [%s]: \n\n", this.filename, date);

                runAnalysis();
                displayAnalysis();

                long timeEnd = System.currentTimeMillis();
                long timeDelta = timeEnd - timeStart;
                double elapsedSeconds = timeDelta / 1000.0;

                view.print("\n\n\tAnalysis took %.0f minutes %.0f seconds to complete.\n\tThis lexical analysis has been " +
                        "saved to a file in the same directory.", elapsedSeconds / 60, elapsedSeconds % 60);
                saveRecordToFile(file, "\n\n\tAnalysis took %.0f minutes %.0f seconds to complete.",
                        elapsedSeconds / 60, elapsedSeconds % 60);

        }

        private String getDate() {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                return dateFormat.format(date);
        }

        private void saveRecordToFile(File file, String text, Object... args) {
                try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                        writer.write(String.format(text, args));
                        writer.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        private void runAnalysis() {
                analysisWord = new StatisticalAnalysis(wordIterator);
                analysisChar = new StatisticalAnalysis(charIterator);
        }

        private void displayAnalysis() {
                displayCharsCount();
                displayCharsNoSpaceCount();
                displayAlphaNumCharsCount();
                displayAllWordsCount();
                displayDictionarySize();
                displayAverageWordLength();
                displaySentencesCount();
                displayAverageSentenceLength();
                displayReadingTime();
                displaySpeakingTime();
                displayWritingTime();
                displayTop30Words();
                displayTop30WordsLongerThan4();
                displayCharsInOrder();
                displayTopKeyword2x();
                displayTopKeyword3x();
        }

        private void displayCharsCount() {
                view.print("\t01. All character count: %d", analysisChar.getAllCount());
                saveRecordToFile(file, "\t01. All character: %d\n", analysisChar.getAllCount());
        }

        private void displayCharsNoSpaceCount() {
                view.print("\t02. All character count (no spaces): %d", analysisChar.getCharNoSpacesCount());
                saveRecordToFile(file, "\t02. All character (no spaces): %d\n", analysisChar.getCharNoSpacesCount());
        }

        private void displayAlphaNumCharsCount() {

                view.print("\t03. Alphanumeric character count: %d", analysisChar.getAlphaNumCount());
                saveRecordToFile(file, "\t03. Alphanumeric character count: %d\n", analysisChar.getAlphaNumCount());
        }

        private void displayAllWordsCount() {

                view.print("\t04. Words count: %d", analysisWord.getAlphaNumCount());
                saveRecordToFile(file, "\t04. Words count: %d\n", analysisWord.getAlphaNumCount());
        }

        private void displayDictionarySize() {
                int dictionarySize = analysisWord.getDictionarySize();

                view.print("\t05. Author's Dictionary (distinct words count): %d", dictionarySize);
                saveRecordToFile(file, "\t05. Author's Dictionary (distinct words count): %d\n", dictionarySize);
        }

        private void displayAverageWordLength() {
                view.print("\t06. Average word length: %d",
                        Math.round((float) analysisChar.getCharNoSpacesCount() / analysisWord.getAlphaNumCount()));
                saveRecordToFile(file, "\t06. Average word length: %d\n",
                        Math.round((float) analysisChar.getCharNoSpacesCount() / analysisWord.getAlphaNumCount()));
        }

        private void displaySentencesCount() {
                view.print("\t07. Sentences count: %d", analysisChar.getSentencesCount());
                saveRecordToFile(file, "\t07. Sentences count: %d\n", analysisChar.getSentencesCount());
        }

        private void displayAverageSentenceLength() {
                int result;
                try {
                        result = Math.round(analysisWord.getAlphaNumCount() / analysisChar.getSentencesCount());
                }catch(ArithmeticException e){
                        result = analysisChar.getAllCount();
                }
                view.print("\t08. Average sentence length: %d words", result);
                saveRecordToFile(file, "\t08. Average sentence length: %d words\n",result);
        }

        private void displayReadingTime(){

        }

        private void displaySpeakingTime(){

        }

        private void displayWritingTime(){

        }

        private void displayTop30Words() {
                view.print("\t12. TOP30 words occuring more than once:\n");
                saveRecordToFile(file, "\t12. TOP30 words occuring more than once:\n\n");

                List<List> topWords = analysisWord.getOccureMoreThanOne();
                int amountOfTopWords = 0;
                try {
                        for (int i = 0; i < 30; i++) {
                                view.print("\t\t*%02d.  %s - %s times", i + 1,
                                        topWords.get(i).get(0), topWords.get(i).get(1));
                                saveRecordToFile(file, "\t\t*%02d.  %s - %s times\n", i + 1,
                                        topWords.get(i).get(0), topWords.get(i).get(1));
                                amountOfTopWords++;
                        }
                } catch (IndexOutOfBoundsException exception) {
                        view.print("\n\t\tOnly %s word(s) occur(s) more than once.",
                                String.valueOf(amountOfTopWords));
                        saveRecordToFile(file, "\n\t\tOnly %s word(s) occur(s) more than once.\n",
                                String.valueOf(amountOfTopWords));
                }
        }

        private void displayTop30WordsLongerThan4() {
                view.print("\n\t13. TOP30 words occuring more than once & longer than 4 letters:\n\n");
                saveRecordToFile(file, "\n\t13. TOP30 words occuring more than once & longer than 4 letters:\n");

                List<List> topWordsMoreThan4 = analysisWord.getWordsMoreThanFour();
                int amountOfTopWords = 0;
                try {
                        for (int i = 0; i < 30; i++) {
                                view.print("\t\t*%02d.  %s - %s times", i + 1,
                                        topWordsMoreThan4.get(i).get(0), topWordsMoreThan4.get(i).get(1));
                                saveRecordToFile(file, "\t\t*%02d.  %s - %s times\n", i + 1,
                                        topWordsMoreThan4.get(i).get(0), topWordsMoreThan4.get(i).get(1));
                                amountOfTopWords++;
                        }
                } catch (IndexOutOfBoundsException exception) {
                        view.print("\n\t\tOnly %s word(s) meet(s) the requirements.",
                                String.valueOf(amountOfTopWords));
                        saveRecordToFile(file, "\n\t\tOnly %s word(s) meet(s) the requirements.\n",
                                String.valueOf(amountOfTopWords));
                }
        }

        private void displayCharsInOrder() {
                view.print("\n\t14. Letters & digits in order of number of occurence count: \n");
                saveRecordToFile(file, "\n\t14. Letters & digits in order of number of occurence count: \n");
                List<List> orderedChars = analysisChar.getOccureMoreThanOne();
                int rankingNumber = 1;

                for (int i = 0; i < orderedChars.size(); i++) {
                        view.print("\t\t*%02d.  %s - %s times", rankingNumber, orderedChars.get(i).get(0),
                                orderedChars.get(i).get(1));
                        saveRecordToFile(file, "\t\t*%02d.  %s - %s times\n", rankingNumber++, orderedChars.get(i).get(0),
                                orderedChars.get(i).get(1));
                }
        }

        private void displayTopKeyword2x() {
                view.print("\n\t15. Keyword density 2x top list: \n\n");
                saveRecordToFile(file, "\n\t15. Keyword density 2x top list: \n\n");
                Map<String, Integer> orderedElements = analysisWord.getElements2xDictionary();
                int rankingNumber = 1;

                for (String key : orderedElements.keySet()) {
                        view.print("\t\t*%02d.  %s - %s times", rankingNumber, key,
                                orderedElements.get(key));
                        saveRecordToFile(file, "\t\t*%02d.  %s - %s times\n", rankingNumber++, key,
                                orderedElements.get(key));
                        if (rankingNumber == 30) {
                                break;
                        }
                }
        }

        private void displayTopKeyword3x() {
                view.print("\n\t14. Keyword density 3x top list: \n\n");
                saveRecordToFile(file, "\n\t14. Keyword density 3x top list: \n\n");
                Map<String, Integer> orderedElements = analysisWord.getElements3xDictionary();
                int rankingNumber = 1;

                for (String key : orderedElements.keySet()) {
                        view.print("\t\t*%02d.  %s - %s times", rankingNumber, key,
                                orderedElements.get(key));
                        saveRecordToFile(file, "\t\t*%02d.  %s - %s times\n", rankingNumber++, key,
                                orderedElements.get(key));
                        if (rankingNumber == 30) {
                                break;
                        }
                }
        }
}



