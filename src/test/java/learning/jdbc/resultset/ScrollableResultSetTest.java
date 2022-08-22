package learning.jdbc.resultset;

import learning.jdbc.statement.JDBCStatementTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class ScrollableResultSetTest {
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
            String sql = "CREATE TABLE Books(Book_Id VARCHAR(6),Title VARCHAR (100),Price INTEGER,Publisher_Id VARCHAR(20))";
            stat.executeUpdate(sql);
            sql = "INSERT INTO Books VALUES('001','Study in Scarlet',20,'0201')";
            stat.executeUpdate(sql);
            sql = "INSERT INTO Books VALUES('002','Four signatures',19,'0471')";
            stat.executeUpdate(sql);
            sql = "INSERT INTO Books VALUES('003','The Hound of the Baskervilles',30,'0471')";
            stat.executeUpdate(sql);
            sql = "INSERT INTO Books VALUES('004','The Valley of Fear',21,'0201')";
            stat.executeUpdate(sql);
            sql = "CREATE TABLE  Publishers (Publisher_Id VARCHAR(6), Name VARCHAR(30), URL VARCHAR(80))";
            stat.executeUpdate(sql);
            sql = "INSERT INTO Publishers VALUES ('0201', 'Addison-Wesley', 'www.aw-nc.com')";
            stat.executeUpdate(sql);
            sql = "INSERT INTO Publishers VALUES ('0471', 'John Wiley & Sons', 'www.wiley.com')";
            stat.executeUpdate(sql);
        }
    }

    @AfterEach
    public void close() throws SQLException {
        try (Statement stat = this.conn.createStatement()) {
            String sql = "DROP TABLE Books";
            stat.executeUpdate(sql);
            sql = "DROP TABLE Publishers";
            stat.executeUpdate(sql);
        }
        if (this.conn != null && !this.conn.isClosed()) {
            this.conn.close();
        }
    }

    @Test
    public void testMoveCursor() throws SQLException {
        String sql = "SELECT Title,Price FROM Books ORDER BY Price DESC";
        Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stat.executeQuery(sql);
        assertFalse(rs.previous());
        assertTrue(rs.isBeforeFirst());
        assertEquals(0, rs.getRow());
        assertFalse(rs.relative(-1));
        assertTrue(rs.relative(1));
        assertTrue(rs.isFirst());
        assertFalse(rs.relative(-1));
        assertTrue(rs.relative(2));
        assertTrue(rs.relative(-1));
        assertFalse(rs.previous());
        assertTrue(rs.absolute(3));
        assertEquals(3, rs.getRow());
        assertFalse(rs.relative(-3));
        assertTrue(rs.isBeforeFirst());
        rs.relative(3);
        String title = rs.getString(1);
        int price = rs.getInt(2);
        assertEquals("Study in Scarlet", title);
        assertEquals(20, price);
    }
}
