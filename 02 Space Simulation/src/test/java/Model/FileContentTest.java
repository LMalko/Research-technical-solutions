package Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FileContentTest {

        private FileContent fileContent;

        @Before
        public void setFileContent() {
                this.fileContent = new FileContent("resources/Intro.txt");
        }

        @After
        public void tearDown() {
                fileContent = null;
        }

        @Test
        public void getDataToCollection() {
                assertFalse(fileContent.getDataToCollection().isEmpty());
                assertEquals(fileContent.getDataToCollection().get(0), "The mission is to send a list of items (Habitats, bunkers, food" +
                        " supplies, and rovers) to Mars, and to pick the correct fleet of rockets.");
        }

        @Test
        public void getDataToString() {
                assertEquals(fileContent.getDataToString().split(" is ")[0], "The mission");
        }
}