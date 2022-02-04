package learning.library.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;

public class FilesTest {

    public void testReadString() throws IOException {
        Path path = Paths.get("test.txt");
        Charset charset = StandardCharsets.UTF_8;
        var content = Files.readString(path, charset);
    }

    public void testWriteString() throws IOException {
        Path path = Paths.get("test.txt");
        Charset charset = StandardCharsets.UTF_8;
        var content = Files.readString(path, charset);
        Files.writeString(path, content);
    }

    public void testWrite() throws IOException {
        Path path = Paths.get("test.txt");
        Charset charset = StandardCharsets.UTF_8;
        var content = Files.readString(path, charset);
        Files.write(path, content.getBytes(charset), StandardOpenOption.APPEND);
    }

    public void testCopy() throws IOException {
        Path fromPath = Paths.get("test.txt");
        Path toPath = Paths.get("test.txt");
        Files.copy(fromPath, toPath, StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES);
    }

    public void testReadBasicFileAttributes() throws IOException {
        Path path = Paths.get("test.txt");
        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
    }

    public void testReadPosixFileAttributes() throws IOException {
        Path path = Paths.get("test.txt");
        PosixFileAttributes attributes = Files.readAttributes(path, PosixFileAttributes.class);
    }

    public void testDirectoryStream() throws IOException {
        Path dir = Paths.get("test");
        DirectoryStream<Path> d = Files.newDirectoryStream(dir, "*.java");
    }

    public void testRecursiveDeleteFile() throws IOException {
        Path root = Paths.get("test");
        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
                if (e != null) throw e;
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public void testZipFileSystemCopy() throws IOException {
        String zipname = "test.zip";
        FileSystem fs = FileSystems.newFileSystem(Paths.get(zipname), null);
    }

    @Test
    public void testZipFileSystemWalkFileTree() throws IOException {
        String zipname = "C:\\Text\\_DEFAULT___DEFAULT__test.war";
        FileSystem fs = FileSystems.newFileSystem(Paths.get(zipname), null);
        Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                System.out.println(file);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
