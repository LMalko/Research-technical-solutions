package Model;

import Iterator.CharIterator;
import Iterator.WordIterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileContentTest {

        private FileContent fileContent;

        @Before
        public void setFileContent() {
                this.fileContent = new FileContent("testText.txt");
        }

        @After
        public void tearDown() {
                fileContent = null;
        }

        @Test
        public void getData() {
                assertFalse(fileContent.getData().isEmpty());
        }

        @Test
        public void charIterator() {
                assertNotNull(this.fileContent.charIterator());
                assertTrue(this.fileContent.charIterator() instanceof CharIterator);
        }

        @Test
        public void wordIterator() {
                assertNotNull(this.fileContent.wordIterator());
                assertTrue(this.fileContent.wordIterator() instanceof WordIterator);
        }

}