package learning.security.signature;

import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MessageDigestTest {

    @Test
    public void testDigest() throws NoSuchAlgorithmException {
        MessageDigest alg = MessageDigest.getInstance("SHA-1");
        byte[] bytes = "hello,world".getBytes();
        alg.update(bytes);
        byte[] hash = alg.digest();
        assertEquals(20, hash.length);
    }

    @Test
    public void testHexString() throws NoSuchAlgorithmException {
        MessageDigest alg = MessageDigest.getInstance("SHA-1");
        byte[] bytes = "hello,world".getBytes();
        alg.update(bytes);
        byte[] hash = alg.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        assertEquals("74f4f4eb1947b9ca08e5e68d04d081808777f9a0", sb.toString());
    }
}
