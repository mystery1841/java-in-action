package learning.jdbc.resultset;


import learning.jdbc.statement.JDBCStatementTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class JDBCResultSetTest {
    private static String url;
    private Connection conn;

    @BeforeAll
    public static void start() throws IOException {
        Properties props = new Properties();
        try (InputStream in = JDBCStatementTest.class.getClassLoader().getResourceAsStream("database.properties")) {
            props.load(in);
        }
        url = props.getProperty("jdbc.url");
    }

    @BeforeEach
    public void setup() throws SQLException {
        this.conn = DriverManager.getConnection(url);
        try (Statement stat = this.conn.createStatement()) {
            String sql = "CREATE TABLE Books(Book_Id VARCHAR(6),Title VARCHAR (100),Price INTEGER,Publisher_Id VARCHAR(20),Cover BLOB)";
            stat.executeUpdate(sql);
        }
    }

    @AfterEach
    public void close() throws SQLException {
        conn.setAutoCommit(true);
        try (Statement stat = this.conn.createStatement()) {
            String sql = "DROP TABLE Books";
            stat.executeUpdate(sql);
        }
        if (this.conn != null && !this.conn.isClosed()) {
            this.conn.close();
        }
    }

    @Test
    public void testReadBlob() throws SQLException, IOException, NoSuchAlgorithmException {
        Blob coverBlob = conn.createBlob();
        int offset = 1;
        OutputStream out = coverBlob.setBinaryStream(offset);
        InputStream in = getClass().getClassLoader().getResourceAsStream("java-logo.png");
        assertNotNull(in);
        MessageDigest digest1 = MessageDigest.getInstance("MD5");
        byte[] bytes = new byte[1024];
        int i;
        while ((i = in.read(bytes)) != -1) {
            out.write(bytes, 0, i);
            digest1.update(bytes, 0, i);
        }
        byte[] expectDigest = digest1.digest();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO Books VALUES ('001','Study in Scarlet',20,'0201',?)");
        statement.setBlob(1, coverBlob);
        int result = statement.executeUpdate();
        assertEquals(1, result);
        conn.setAutoCommit(false);
        Statement stat = conn.createStatement();
        ResultSet resultSet = stat.executeQuery("SELECT Title,Cover FROM Books");
        String title = null;
        Blob blob = null;
        while (resultSet.next()) {
            title = resultSet.getString(1);
            blob = resultSet.getBlob(2);
        }
        assertEquals("Study in Scarlet", title);

        assertNotNull(blob);
        InputStream image = blob.getBinaryStream();
        MessageDigest digest2 = MessageDigest.getInstance("MD5");
        byte[] imageArray = new byte[1024];
        int ch;
        while ((ch = image.read(imageArray)) != -1) {
            digest2.update(imageArray, 0, ch);
        }
        byte[] digestResult = digest2.digest();
        assertArrayEquals(expectDigest, digestResult);
        conn.commit();
    }
}
