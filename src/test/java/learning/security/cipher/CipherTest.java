package learning.security.cipher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;

public class CipherTest {

    @Test
    public void testCipherStream() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        CipherUtil cipherUtil = new CipherUtil();
        cipherUtil.crypt();
        String result = cipherUtil.decrypt();
        Assertions.assertEquals("HELLO,WORLD;", result);
    }

    @Test
    public void testKeyPairGenerator() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
        int KEYSIZE = 1024;
        KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
        var random = new SecureRandom();
        pairgen.initialize(KEYSIZE, random);
        KeyPair keyPair = pairgen.generateKeyPair();
        Key publicKey = keyPair.getPublic();
        Key privateKey = keyPair.getPrivate();
        CipherUtil cipherUtil = new CipherUtil();
        cipherUtil.crypt(publicKey);
        String result = cipherUtil.decrypt(privateKey);
        Assertions.assertEquals("HELLO,WORLD;", result);
    }

    @Test
    public void testWrapKey() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException {
        CipherUtil cipherUtil = new CipherUtil();
        cipherUtil.crypt();
        int BLOCKSIZE = 1024;
        int KEYSIZE = 1024;
        byte[] keyBytes = new byte[BLOCKSIZE];
        InputStream in2 = new FileInputStream("C:\\Text\\secret.key");
        int length = in2.read(keyBytes);
        Key key = new SecretKeySpec(keyBytes, 0, length, "AES");
        KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
        var random = new SecureRandom();
        pairgen.initialize(KEYSIZE, random);
        KeyPair keyPair = pairgen.generateKeyPair();
        Key publicKey = keyPair.getPublic();
        Key privateKey = keyPair.getPrivate();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.WRAP_MODE, publicKey);
        byte[] wrappedKey = cipher.wrap(key);
        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.UNWRAP_MODE, privateKey);
        Key unwrappedKey = cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);
        String result = cipherUtil.decrypt(unwrappedKey.getEncoded());
        Assertions.assertEquals("HELLO,WORLD;", result);
    }

}
