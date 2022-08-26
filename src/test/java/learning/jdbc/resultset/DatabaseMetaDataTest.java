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

public class DatabaseMetaDataTest {
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
            String sql = "CREATE TABLE Books(Book_Id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY ,Title VARCHAR (100),Price INTEGER,Publisher_Id VARCHAR(20),Cover BLOB)";
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
    public void testDatabaseMetaData() throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet mrs = meta.getTables(null, null, null, new String[]{"TABLE"});
        String tableName = null;
        while (mrs.next())
            tableName = mrs.getString(3);
        assertEquals("BOOKS", tableName);
    }

    @Test
    public void testResultSetMetaData() throws SQLException {
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
        String tableName = null;
        while (resultSet.next())
            tableName = resultSet.getString(3);
        assertEquals("BOOKS", tableName);
        Statement stat = conn.createStatement();
        stat.executeUpdate("INSERT INTO Books(Title,Price,Publisher_Id) values('Study in Scarlet',20,'0201')");
        ResultSet mrs = stat.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData meta = mrs.getMetaData();
        String columnName = meta.getColumnLabel(2);
        int columnWidth = meta.getColumnDisplaySize(2);
        assertEquals("TITLE", columnName);
        assertEquals(100, columnWidth);
    }
}
