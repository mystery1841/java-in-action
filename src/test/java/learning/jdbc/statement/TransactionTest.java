package learning.jdbc.statement;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import java.io.InputStream;
import java.sql.*;

public class TransactionTest extends DBTestCase {
    private Connection conn;


    public TransactionTest(String name) {
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


    public void testTransaction() throws Exception {
        this.conn.setAutoCommit(false);
        try (Statement stat = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            ResultSet rs = stat.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                if (rs.getString("title").equals("Study in Scarlet")) {
                    rs.updateInt("price", 29);
                    rs.updateRow();
                }
            }
        }
        this.conn.commit();
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("books");
        InputStream in = getClass().getClassLoader().getResourceAsStream("database/expect/jdbc-transaction-commit.xml");
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(in);
        ITable expectedTable = expectedDataSet.getTable("books");
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
                expectedTable.getTableMetaData().getColumns());
        Assertion.assertEquals(expectedTable, filteredTable);
    }

    public void testTransactionRollback() throws Exception {
        this.conn.setAutoCommit(false);
        try (Statement stat = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);) {
            ResultSet rs = stat.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                if (rs.getString("title").equals("Study in Scarlet")) {
                    rs.updateInt("price", 29);
                    rs.updateRow();
                }
            }
        }
        this.conn.rollback();
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("books");
        InputStream in = getClass().getClassLoader().getResourceAsStream("database/books.xml");
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(in);
        ITable expectedTable = expectedDataSet.getTable("books");
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
                expectedTable.getTableMetaData().getColumns());
        Assertion.assertEquals(expectedTable, filteredTable);
    }

    public void testSavePoint() throws Exception {
        conn.setAutoCommit(false);
        Statement stat = conn.createStatement();
        stat.executeUpdate("UPDATE Books SET Price = 10 WHERE Title='Study in Scarlet'");
        Savepoint svpt = conn.setSavepoint();
        stat.executeUpdate("UPDATE Books SET Price = 100 WHERE Title='Four signatures'");
        conn.rollback(svpt);
        conn.commit();
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("books");
        InputStream in = getClass().getClassLoader().getResourceAsStream("database/expect/jdbc-transaction-savepoint.xml");
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(in);
        ITable expectedTable = expectedDataSet.getTable("books");
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable,
                expectedTable.getTableMetaData().getColumns());
        Assertion.assertEquals(expectedTable, filteredTable);
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        InputStream in = getClass().getClassLoader().getResourceAsStream("database/books.xml");
        return new FlatXmlDataSetBuilder().build(in);
    }
}
