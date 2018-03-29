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

        public void results(){
                System.out.println(String.format("\n\nDOCUMENT %s LEXICAL ANALYSIS: \n\n", this.filename));

                StatisticalAnalysis analysisChar = new StatisticalAnalysis(new CharIterator(fileContent.textContent()));
                System.out.println(String.format("\tAlphanumeric character count: %d", analysisChar.getCount()));

                StatisticalAnalysis analysisWord = new StatisticalAnalysis(new WordIterator(fileContent.textContent()));
                System.out.println(String.format("\tWords count: %d", analysisWord.getCount()));


                analysisWord = new StatisticalAnalysis(new WordIterator(fileContent.textContent()));
                int dictionarySize = analysisWord.dictionarySize();
                System.out.println(String.format("\tAuthor's Dictionary (distinct words count): %d", dictionarySize));

                analysisWord = new StatisticalAnalysis(new WordIterator(fileContent.textContent()));
                System.out.println("\tTOP20 words occuring more than once:\n");
                Object[] topWords = analysisWord.occurMoreThan(1).toArray();
                int amountOfTopWords = 0;
                try {
                        for (int i = 0; i < 20; i++) {
                                System.out.println(String.format("\t\t%d. %s", i, topWords[i]));
                                amountOfTopWords ++;
                        }
                }catch(ArrayIndexOutOfBoundsException exception){
                        System.out.println(String.format("\n\t\tOnly %s word(s) occur more than once.", String.valueOf(amountOfTopWords)));
                }
        }

}
