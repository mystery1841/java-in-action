package learning.io.binary;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class OutputStreamTest {

    @Test
    public void testWrite() throws IOException {
        var out = new FileOutputStream("C:\\Text\\write.txt");
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out,StandardCharsets.UTF_8));
        pw.println("hello world");
        pw.close();
    }
}
