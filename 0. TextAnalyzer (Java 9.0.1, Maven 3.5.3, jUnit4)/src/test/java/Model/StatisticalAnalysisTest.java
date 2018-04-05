package Model;

import Iterator.CharIterator;
import Iterator.WordIterator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


import static org.junit.Assert.*;

public class StatisticalAnalysisTest {

        private StatisticalAnalysis charAnalysis, wordAnalysis;

        @Before
        public void setUp() {
                this.charAnalysis = new StatisticalAnalysis(
                        new CharIterator(new FileContent("testText.txt")));
                this.wordAnalysis = new StatisticalAnalysis(
                        new WordIterator(new FileContent("testText.txt")));
        }

        @Test
        public void getCount() {
                assertEquals(this.charAnalysis.getCount(), 955386);
                assertEquals(this.wordAnalysis.getCount(), 219043);

        }

        @Test
        public void dictionarySize() {
                assertEquals(this.wordAnalysis.dictionarySize(), 16959);
        }

        @Test
        public void occurMoreThan() {
                assertTrue(this.charAnalysis.occurMoreThan(1).get(0).get(0).toString().matches("[a-zA-Z0-9]"));
                assertTrue(this.wordAnalysis.occurMoreThan(7).get(0).get(1).toString().matches("[8-9]|[0-9]{2,}"));
        }

        @Test
        public void wordsLenMoreThan4() {
                assertTrue(this.wordAnalysis.wordsLenMoreThan4().get(0).get(0).toString().matches("[a-zA-Z0-9]{4,}"));
                assertEquals(this.charAnalysis.wordsLenMoreThan4(), new ArrayList<>());
        }
}