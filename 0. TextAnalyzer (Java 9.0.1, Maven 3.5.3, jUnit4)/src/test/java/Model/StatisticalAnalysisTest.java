package Model;

import Iterator.CharIterator;
import Iterator.WordIterator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


import static org.junit.Assert.*;

public class StatisticalAnalysisTest {

        private WordIterator wordIterator = new WordIterator(new FileContent("testText.txt"));
        private CharIterator charIterator = new CharIterator(new FileContent("testText.txt"));

        private StatisticalAnalysis wordAnalysis = new StatisticalAnalysis(wordIterator);
        private StatisticalAnalysis charAnalysis = new StatisticalAnalysis(charIterator);


        @Before
        public void setUp() {
                wordIterator.restartIterator();
                charIterator.restartIterator();
        }

        @Test
        public void getCount() {
                assertEquals(this.charAnalysis.getAlphaNumCount(), 955386);
                assertEquals(this.wordAnalysis.getAlphaNumCount(), 219044);

        }

        @Test
        public void dictionarySize() {
                assertEquals(this.wordAnalysis.dictionarySize(), 16958);
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