package Model;

import Iterator.CharIterator;
import Iterator.WordIterator;
import org.junit.Test;

import java.util.ArrayList;


import static org.junit.Assert.*;

public class StatisticalAnalysisTest {

        private WordIterator wordIterator = new WordIterator(new FileContent("testText.txt"));
        private CharIterator charIterator = new CharIterator(new FileContent("testText.txt"));

        private StatisticalAnalysis wordAnalysis = new StatisticalAnalysis(wordIterator);
        private StatisticalAnalysis charAnalysis = new StatisticalAnalysis(charIterator);

        @Test
        public void getCount() {
                assertEquals(this.charAnalysis.getAlphaNumElementsCount(), 15085);
                assertEquals(this.wordAnalysis.getAlphaNumElementsCount(), 3513);

        }

        @Test
        public void dictionarySize() {
                assertEquals(this.wordAnalysis.getDictionarySize(), 1100);
        }

        @Test
        public void occurMoreThan() {
                assertTrue(this.charAnalysis.getOccureMoreThanOne().get(0).get(0).toString().matches("[a-zA-Z0-9]"));
                assertTrue(this.wordAnalysis.getOccureMoreThanOne().get(0).get(1).toString().matches("[8-9]|[0-9]{2,}"));
        }

        @Test
        public void wordsLenMoreThan4() {
                assertTrue(this.wordAnalysis.getWordsMoreThanFour().get(0).get(0).toString().matches("[a-zA-Z0-9]{4,}"));
                assertEquals(this.charAnalysis.getWordsMoreThanFour(), new ArrayList<>());
        }
}