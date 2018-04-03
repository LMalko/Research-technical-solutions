package Model;

import Iterator.CharIterator;
import Iterator.WordIterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;


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

        @Test(expected = IOException.class)
        public void createFileContentWithNotExistingFile() {
                new FileContent("file");
        }

        @Test
        public void getData() {
                
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