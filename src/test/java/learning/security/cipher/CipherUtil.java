package learning.security.cipher;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class CipherUtil {
    private InputStream in;
    private StringBuilder sb = new StringBuilder();

    public static void crypt(InputStream in, OutputStream out, Cipher cipher)
            throws IOException, GeneralSecurityException {
        int blockSize = cipher.getBlockSize();
        int outputSize = cipher.getOutputSize(blockSize);
        var inBytes = new byte[blockSize];
        var outBytes = new byte[outputSize];
        int inLength = 0;
        boolean more = true;
        while (more) {
            inLength = in.read(inBytes);
            if (inLength == blockSize) {
                int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
                out.write(outBytes, 0, outLength);
            } else
                more = false;

        }
        if (inLength > 0)
            outBytes = cipher.doFinal(inBytes, 0, inLength);
        else
            outBytes = cipher.doFinal();
        out.write(outBytes);
    }

    public void crypt() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        int BLOCKSIZE = 1024;
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        var random = new SecureRandom();
        keygen.init(random);
        Key key = keygen.generateKey();
        String outputFileName = "C:\\Text\\secret.txt";
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        try (var out = new CipherOutputStream(new FileOutputStream(outputFileName), cipher);
             var out2 = new FileOutputStream("C:\\Text\\secret.key")) {
            var bytes = new byte[BLOCKSIZE];
            int inLength = getData(bytes); // get more data from data source
            while (inLength != -1) {
                out.write(bytes, 0, inLength);
                inLength = getData(bytes);
            }
            out.flush();
            out2.write(key.getEncoded());
        }
    }

    public String decrypt() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        int BLOCKSIZE = 1024;
        String inputFileName = "C:\\Text\\secret.txt";
        byte[] keyBytes = new byte[BLOCKSIZE];
        InputStream in2 = new FileInputStream("C:\\Text\\secret.key");
        int length = in2.read(keyBytes);
        Key key = new SecretKeySpec(keyBytes,0,length,"AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        var in = new CipherInputStream(new FileInputStream(inputFileName), cipher);
        var bytes = new byte[BLOCKSIZE];
        int inLength = in.read(bytes);
        while (inLength != -1) {
            putData(bytes, inLength); // put data to destination
            inLength = in.read(bytes);
        }
        return sb.toString();
    }

    public int getData(byte[] bytes) throws IOException {
        if (in == null) {
            in = new FileInputStream("C:\\Text\\test.txt");
        }
        return in.read(bytes);
    }

    public void putData(byte[] bytes, int inLength) {
        sb.append(new String(bytes, 0, inLength, StandardCharsets.UTF_8));
    }

    public void crypt(Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        int BLOCKSIZE = 1024;
        String outputFileName = "C:\\Text\\secret.txt";
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        try (var out = new CipherOutputStream(new FileOutputStream(outputFileName), cipher)) {
            var bytes = new byte[BLOCKSIZE];
            int inLength = getData(bytes); // get more data from data source
            while (inLength != -1) {
                out.write(bytes, 0, inLength);
                inLength = getData(bytes);
            }
            out.flush();
        }
    }
    public String decrypt(Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        int BLOCKSIZE = 1024;
        String inputFileName = "C:\\Text\\secret.txt";
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        var in = new CipherInputStream(new FileInputStream(inputFileName), cipher);
        var bytes = new byte[BLOCKSIZE];
        int inLength = in.read(bytes);
        while (inLength != -1) {
            putData(bytes, inLength); // put data to destination
            inLength = in.read(bytes);
        }
        return sb.toString();
    }
    public String decrypt(byte[] KeyBytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        int BLOCKSIZE = 1024;
        String inputFileName = "C:\\Text\\secret.txt";
        Key key = new SecretKeySpec(KeyBytes,"AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        var in = new CipherInputStream(new FileInputStream(inputFileName), cipher);
        var bytes = new byte[BLOCKSIZE];
        int inLength = in.read(bytes);
        while (inLength != -1) {
            putData(bytes, inLength); // put data to destination
            inLength = in.read(bytes);
        }
        return sb.toString();
    }


}



