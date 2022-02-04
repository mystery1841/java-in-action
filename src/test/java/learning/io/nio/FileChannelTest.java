package learning.io.nio;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

public class FileChannelTest {

    public void testMap() throws IOException {
        Path filename = Paths.get("test.txt");
        try (FileChannel channel = FileChannel.open(filename)) {
            var crc = new CRC32();
            int length = (int) channel.size();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
            for (int p = 0; p < length; p++) {
                int c = buffer.get(p);
                crc.update(c);
            }
        }
    }
}
