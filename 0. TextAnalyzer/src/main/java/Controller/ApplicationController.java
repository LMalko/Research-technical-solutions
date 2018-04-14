package Controller;

import Enum.TimeConvert;
import Enum.WordsToTime;
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
        private boolean toPrint;
        private boolean toSaveToFile;
        private View view = new View();

        private WordIterator wordIterator;
        private CharIterator charIterator;

        private StatisticalAnalysis analysisWord;
        private StatisticalAnalysis analysisChar;
        private List<String> analysisResults = new ArrayList<>();


        public ApplicationController(String filename, boolean toPrint, boolean toSaveToFile) {
                this.filename = filename;
                this.toPrint = toPrint;
                this.toSaveToFile = toSaveToFile;

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
                view.print("It takes few minutes for longer texts.\n\n");

                analysisResults.add(String.format("\n\nDOCUMENT %s LEXICAL ANALYSIS [%s]: \n\n", this.filename, date));
                runAnalysis();
                gatherAnalysis();

                long timeEnd = System.currentTimeMillis();
                long timeDelta = timeEnd - timeStart;
                double elapsedSeconds = timeDelta / TimeConvert.MILISEC_IN_SEC.getConvert();

                analysisResults.add(String.format("\n\n\tAnalysis took %.0f minutes %.0f seconds to complete.\n\t",
                        elapsedSeconds / TimeConvert.SECS_IN_MIN.getConvert(),
                        elapsedSeconds % TimeConvert.SECS_IN_MIN.getConvert()));
                analysisResults.add("This lexical analysis has been saved to a file in the same directory.");

                resolveAnalysis();
        }

        private void resolveAnalysis(){
                if(toPrint){
                        for(String text: analysisResults){
                                view.print(text);
                        }
                }
                if(toSaveToFile){
                        int noNeedToPrint = analysisResults.size() - 1;
                        analysisResults.remove(noNeedToPrint);
                        for(String text: analysisResults){
                                saveRecordToFile(file, String.format("%s\n", text));
                        }
                }
        }

        private String getDate() {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                return dateFormat.format(date);
        }

        private void saveRecordToFile(File file, String text) {
                try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                        writer.write(text);
                        writer.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        private void runAnalysis() {
                analysisWord = new StatisticalAnalysis(wordIterator);
                analysisChar = new StatisticalAnalysis(charIterator);
        }

        private void gatherAnalysis() {
                gatherCharsCount();
                gatherCharsNoSpaceCount();
                gatherAlphaNumCharsCount();
                gatherAllWordsCount();
                gatherDictionarySize();
                gatherAverageWordLength();
                gatherSentencesCount();
                gatherAverageSentenceLength();
                gatherReadingTime();
                gatherSpeakingTime();
                gatherWritingTime();
                gatherTopCollection("\t12. TOP30 words occuring more than once:\n",
                                                    analysisWord.getOccureMoreThanOne(), 30);
                gatherTopCollection("\n\t13. TOP30 words occuring more than once & longer than 4 letters:\n\n",
                                                    analysisWord.getWordsMoreThanFour(), 30);
                gatherTopCollection("\n\t14. Letters & digits in order of number of occurence count: \n",
                                                    analysisChar.getOccureMoreThanOne(), 0);
                gatherTopKeywordDensity("\n\t15. Keyword density 2x top list: \n\n",
                        analysisWord.getElements2xDictionary(), 30);
                gatherTopKeywordDensity("\n\t16. Keyword density 3x top list: \n\n",
                        analysisWord.getElements3xDictionary(), 30);
        }

        private void gatherCharsCount() {
                analysisResults.add(String.format("\t01. All character count: %d", analysisChar.getAllElementsCount()));
        }

        private void gatherCharsNoSpaceCount() {
                analysisResults.add(String.format("\t02. All character count (no spaces): %d", analysisChar.getCharNoSpacesCount()));
        }

        private void gatherAlphaNumCharsCount() {
                analysisResults.add(String.format("\t03. Alphanumeric character count: %d", analysisChar.getAlphaNumElementsCount()));
        }

        private void gatherAllWordsCount() {
                analysisResults.add(String.format("\t04. Words count: %d", analysisWord.getAlphaNumElementsCount()));
        }

        private void gatherDictionarySize() {
                int dictionarySize = analysisWord.getDictionarySize();
                analysisResults.add(String.format("\t05. Author's Dictionary (distinct words count): %d", dictionarySize));
        }

        private void gatherAverageWordLength() {
                analysisResults.add(String.format("\t06. Average word length: %d",
                        Math.round((float) analysisChar.getCharNoSpacesCount() / analysisWord.getAlphaNumElementsCount())));
        }

        private void gatherSentencesCount() {
                analysisResults.add(String.format("\t07. Sentences count: %d", analysisChar.getSentencesCount()));
        }

        private void gatherAverageSentenceLength() {
                int result;
                try {
                        result = Math.round(analysisWord.getAlphaNumElementsCount() / analysisChar.getSentencesCount());
                }catch(ArithmeticException e){
                        result = analysisChar.getAllElementsCount();
                }
                analysisResults.add(String.format("\t08. Average sentence length: %d words", result));
        }

        private void gatherReadingTime(){
                analysisResults.add(String.format("\t09. Reading time based on average speed 275 words per minute: %s",
                                analysisWord.getFormattedWordsToTime(WordsToTime.READ_PER_MIN.getConvert(),
                                WordsToTime.READ_PER_HOUR.getConvert(),
                                WordsToTime.READ_PER_DAY.getConvert())));
        }

        private void gatherSpeakingTime(){
                analysisResults.add(String.format("\t10. Speaking time based on average speed 180 words per minute: %s",
                                analysisWord.getFormattedWordsToTime(WordsToTime.SPEAK_PER_MIN.getConvert(),
                                WordsToTime.SPEAK_PER_HOUR.getConvert(),
                                WordsToTime.SPEAK_PER_DAY.getConvert())));
        }

        private void gatherWritingTime(){
                analysisResults.add(String.format("\t11. Hand-writing time based on average speed 68 letters per minute: %s",
                                analysisChar.getFormattedWordsToTime(WordsToTime.WRITE_PER_MIN.getConvert(),
                                WordsToTime.WRITE_PER_HOUR.getConvert(),
                                WordsToTime.WRITE_PER_DAY.getConvert())));
        }

        private void gatherTopCollection(String header, List<HashMap> collection, int top) {
                analysisResults.add(header);
                int counter = 1;

                a: for (HashMap pair : collection) {
                        b: for (Object key : pair.keySet()) {
                                analysisResults.add(String.format("\t\t*%02d.  %s - %s times", counter, key, pair.get(key)));
                                counter++;
                                if (counter > top && top != 0) {
                                        break a;
                                }
                        }
                }
                if (counter < top && top != 0) {
                        analysisResults.add(String.format("\n\t\tOnly %s word(s) occur(s) more than once.",
                                String.valueOf(counter - 1)));
                }
        }

        private void gatherTopKeywordDensity(String header, Map<String, Integer> elements, int top) {
                analysisResults.add(header);

                int rankingNumber = 1;

                for (String key : elements.keySet()) {
                        analysisResults.add(String.format("\t\t*%02d.  %s - %s times", rankingNumber, key, elements.get(key)));
                        rankingNumber++;
                        if (rankingNumber > 30) {
                                break;
                        }
                }
        }
}



