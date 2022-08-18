package learning.jdbc.statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class JDBCStatementTest {
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
    public void testPreparedStatement() throws SQLException {
        String publisherQuery = "SELECT Books.Price, Books.Title" +
                " FROM Books, Publishers" +
                " WHERE Books.Publisher_Id = Publishers.Publisher_Id AND Publishers.Name = ?";
        PreparedStatement stat = conn.prepareStatement(publisherQuery);
        String publisher = "Addison-Wesley";
        stat.setString(1, publisher);
        ResultSet rs = stat.executeQuery();
        int price = 0;
        String title = "";
        while (rs.next()) {
            price = rs.getInt(1);
            title = rs.getString(2);
        }
        assertEquals(20, price);
        assertEquals("Study in Scarlet", title);
    }
}
