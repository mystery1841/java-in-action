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
        OutputStream out = coverBlob.setBinaryStream(1);
        InputStream javaLogo = getClass().getClassLoader().getResourceAsStream("java-logo.png");
        assertNotNull(javaLogo);
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] bytes = new byte[1024];
        int i;
        while ((i = javaLogo.read(bytes)) != -1) {
            out.write(bytes, 0, i);
            digest.update(bytes, 0, i);
        }
        byte[] expectDigest = digest.digest();
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
        InputStream in = blob.getBinaryStream();
        while ((i = in.read(bytes)) != -1) {
            digest.update(bytes, 0, i);
        }
        byte[] digestResult = digest.digest();
        assertArrayEquals(expectDigest, digestResult);
        conn.commit();
    }

    public void testMultipleResultSet() throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO Books VALUES ('001','Study in Scarlet',20,'0201',?)");
        statement.setBlob(1, (Blob) null);
        int result = statement.executeUpdate();
        assertEquals(1, result);
        String sql = "SELECT Title,Cover FROM Books ; SELECT Title,Cover FROM Books";
        Statement stat = conn.createStatement();
        String name = null;
        String name2 = null;
        boolean isResult = stat.execute(sql);
        if (isResult) {
            ResultSet r = statement.getResultSet();
            while (r.next()) {
                name = r.getString(1);
            }
        }
        isResult = stat.getMoreResults();
        if (isResult) {
            ResultSet r = statement.getResultSet();
            while (r.next()) {
                name2 = r.getString(1);
            }
        }
        assertEquals("Study in Scarlet",name);
        assertEquals("Study in Scarlet",name2);
    }
}
