package learning.io.binary;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CompressionTest {

    @Test
    public void testReadZip() throws IOException {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream("C:\\Text\\_DEFAULT___DEFAULT__test.war"))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null)
            {
                System.out.println(entry.getName());
                zin.closeEntry();
            }

        }
    }


}
