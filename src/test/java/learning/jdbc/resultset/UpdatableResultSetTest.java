package learning.jdbc.resultset;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdatableResultSetTest extends DBTestCase {
    private Connection conn;

    public UpdatableResultSetTest(String name) throws Exception {
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                "org.apache.derby.jdbc.EmbeddedDriver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                "jdbc:derby:memory:COREJAVA;create=true");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        this.conn = getConnection().getConnection();
        try (Statement stat = this.conn.createStatement()) {
            stat.executeUpdate("CREATE TABLE books(id Integer NOT NULL GENERATED ALWAYS AS IDENTITY" +
                    ",title VARCHAR (100),price INTEGER,publisher_id Integer) ");
        }
        return DatabaseOperation.CLEAN_INSERT;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        InputStream in = getClass().getClassLoader().getResourceAsStream("database/books.xml");
        return new FlatXmlDataSetBuilder().build(in);
    }

    public void testUpdateRow() throws Exception {
        try (Statement stat = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stat.executeQuery("SELECT * FROM books")) {
            assertEquals(ResultSet.CONCUR_UPDATABLE, rs.getConcurrency());
            while (rs.next()) {
                if ("Four signatures".equals(rs.getString("title"))) {
                    rs.updateInt("price", 18);
                    rs.updateRow();
                }
            }
        }
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("books");
        InputStream in = getClass().getClassLoader().getResourceAsStream("database/expect/update-row.xml");
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(in);
        ITable expectedTable = expectedDataSet.getTable("books");
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
                expectedTable.getTableMetaData().getColumns());
        Assertion.assertEquals(expectedTable, filteredTable);
    }

    public void testCancelRowUpdates() throws Exception {
        try (Statement stat = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stat.executeQuery("SELECT * FROM books")) {
            assertEquals(ResultSet.CONCUR_UPDATABLE, rs.getConcurrency());
            while (rs.next()) {
                if ("Four signatures".equals(rs.getString("title"))) {
                    rs.updateInt("price", 18);
                    rs.cancelRowUpdates();
                    rs.updateRow();
                }
            }
        }
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("books");
        InputStream in = getClass().getClassLoader().getResourceAsStream("database/expect/cancel-update.xml");
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(in);
        ITable expectedTable = expectedDataSet.getTable("books");
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
                expectedTable.getTableMetaData().getColumns());
        Assertion.assertEquals(expectedTable, filteredTable);
    }

    public void testInsert() throws Exception {
        try (Statement stat = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stat.executeQuery("SELECT * FROM books")) {
            assertEquals(ResultSet.CONCUR_UPDATABLE, rs.getConcurrency());
            rs.moveToInsertRow();
            rs.updateString("title", "The Hound of the Baskervilles");
            rs.updateInt("price", 30);
            rs.updateInt("publisher_id", 471);
            rs.insertRow();
            rs.moveToCurrentRow();
        }
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("books");
        InputStream in = getClass().getClassLoader().getResourceAsStream("database/expect/insert-row.xml");
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(in);
        ITable expectedTable = expectedDataSet.getTable("books");
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
                expectedTable.getTableMetaData().getColumns());
        Assertion.assertEquals(expectedTable, filteredTable);
    }

    public void testDelete() throws Exception {
        try (Statement stat = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stat.executeQuery("SELECT * FROM books")) {
            assertEquals(ResultSet.CONCUR_UPDATABLE, rs.getConcurrency());
            while (rs.next()) {
                if ("Four signatures".equals(rs.getString("title"))) {
                    rs.deleteRow();
                }
            }
        }
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("books");
        InputStream in = getClass().getClassLoader().getResourceAsStream("database/expect/delete-row.xml");
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(in);
        ITable expectedTable = expectedDataSet.getTable("books");
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
                expectedTable.getTableMetaData().getColumns());
        Assertion.assertEquals(expectedTable, filteredTable);
    }
}
