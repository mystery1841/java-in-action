package learning.io.character;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Stream;

public class WriterTest {

    public void testPrintWriter() throws IOException {
        var out = new PrintWriter("employee.txt", StandardCharsets.UTF_8);
    }

    public void testPri() throws IOException{
        var out = new PrintWriter(
                new OutputStreamWriter(
                        new FileOutputStream("employee.txt"), StandardCharsets.UTF_8),
        true);
    }
}
