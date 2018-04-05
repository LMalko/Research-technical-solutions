package Model;

import Iterator.CharIterator;
import Iterator.WordIterator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticalAnalysisTest {

        private StatisticalAnalysis charAnalysis, wordAnalysis;

        @Before
        public void setUp() {
                charAnalysis = new StatisticalAnalysis(
                        new CharIterator(new FileContent("testText.txt")));
                wordAnalysis = new StatisticalAnalysis(
                        new WordIterator(new FileContent("testText.txt")));
        }

        @Test
        public void getCount() {
                assertEquals(charAnalysis.getCount(), 955386);
                assertEquals(wordAnalysis.getCount(), 219043);

        }

        @Test
        public void dictionarySize() {
                wordAnalysis = new StatisticalAnalysis(new WordIterator(new FileContent("testText.txt")));
                assertEquals(wordAnalysis.dictionarySize(), 16959);
        }

        @Test
        public void occurMoreThan() {
                charAnalysis = new StatisticalAnalysis(new CharIterator(new FileContent("testText.txt")));
                assertTrue(charAnalysis.occurMoreThan(1).get(0).get(0).toString().matches("[a-zA-Z0-9]"));
                charAnalysis = new StatisticalAnalysis(new CharIterator(new FileContent("testText.txt")));
                assertTrue(charAnalysis.occurMoreThan(1).get(0).get(1).toString().matches("[0-9]+"));
        }

        @Test
        public void wordsLenMoreThan4() {
                wordAnalysis = new StatisticalAnalysis(new WordIterator(new FileContent("testText.txt")));
                assertTrue(wordAnalysis.wordsLenMoreThan4().get(0).get(0).toString().matches("[a-zA-Z0-9]{4,}"));
                wordAnalysis = new StatisticalAnalysis(new WordIterator(new FileContent("testText.txt")));
                assertTrue(wordAnalysis.wordsLenMoreThan4().get(0).get(1).toString().matches("[0-9]+"));
        }
}