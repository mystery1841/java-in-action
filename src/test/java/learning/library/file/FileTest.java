package learning.library.file;

import java.io.File;
import java.io.FileNotFoundException;

public class FileTest {

    public void testCreateNewFile() throws FileNotFoundException {
        File f = new File("test.txt");
    }
}
