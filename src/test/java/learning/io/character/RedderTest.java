package learning.io.character;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class RedderTest {

    public void testCreateReader() throws FileNotFoundException {
        var in = new InputStreamReader(new FileInputStream("data.txt"), StandardCharsets.UTF_8);

    }

}
