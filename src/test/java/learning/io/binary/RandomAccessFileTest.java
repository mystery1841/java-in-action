package learning.io.binary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.ZipOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class RandomAccessFileTest {
    public static String file = "C:\\Text\\test.txt";

    @BeforeEach
    public void startup() {
        File f = new File(file);
        if (f.exists()) {
            f.delete();
        }
    }

    @Test
    public void testSeed() throws IOException {
        try (RandomAccessFile rf = new RandomAccessFile(file, "rw")) {
            for (int i = 0; i < 7; i++) {
                rf.writeDouble(i * 1.414);
            }
            rf.writeUTF("The end of the file");
            rf.seek(5 * 8);
            rf.writeDouble(47.0001);
            rf.writeDouble(47.0002);
        }
        try (RandomAccessFile rf = new RandomAccessFile(file, "r")) {
            double[] array = new double[7];
            for (int i = 0; i < 7; i++) {
                array[i] = rf.readDouble();
            }
            String s = rf.readUTF();
            assertEquals(47.0001, array[5]);
            assertEquals(47.0002, array[6]);
            assertEquals("The end of the file", s);
        }
    }

}
