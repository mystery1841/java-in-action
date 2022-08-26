package learning.jdbc.resultset;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CachedRowSetTest extends DBTestCase {
    private Connection conn;

    public CachedRowSetTest(String name) {
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

    public void testPopulateResultSet() throws SQLException {
        Statement stat = this.conn.createStatement();
        ResultSet result = stat.executeQuery("SELECT * FROM books");
        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet crs = factory.createCachedRowSet();
        crs.populate(result);
        conn.close();
        Set<String> set = new HashSet<>();
        while (crs.next()) {
            set.add(crs.getString("title"));
        }
        assertEquals(2, set.size());
        assertThat(set, containsInAnyOrder("Study in Scarlet", "Four signatures"));
    }

    public void testCreateConnection() throws SQLException {
        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet crs = factory.createCachedRowSet();
        crs.setUrl("jdbc:derby:memory:COREJAVA;create=true");
        crs.setUsername("sa");
        crs.setPassword("");
        crs.setCommand("SELECT * FROM Books WHERE Publisher_ID = ?");
        crs.setString(1, "201");
        crs.execute();
        Set<String> set = new HashSet<>();
        while (crs.next()) {
            set.add(crs.getString("title"));
        }
        assertEquals(1, set.size());
        assertThat(set, containsInAnyOrder("Study in Scarlet"));
    }

    public void testSetPageSize() throws SQLException {
        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet crs = factory.createCachedRowSet();
        crs.setUrl("jdbc:derby:memory:COREJAVA;create=true");
        crs.setUsername("sa");
        crs.setPassword("");
        crs.setCommand("SELECT * FROM Books");
        crs.setPageSize(1);
        crs.execute();
        Set<String> set = new HashSet<>();
        while (crs.next()) {
            set.add(crs.getString("title"));
        }
        assertEquals(1, set.size());
        assertTrue(crs.isAfterLast());
        assertTrue(crs.nextPage());
        while (crs.next()) {
            set.add(crs.getString("title"));
        }
        assertEquals(2, set.size());
        assertThat(set, containsInAnyOrder("Study in Scarlet", "Four signatures"));
    }

    public void testAcceptChanges() throws Exception {
        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet crs = factory.createCachedRowSet();
        crs.setUrl("jdbc:derby:memory:COREJAVA;create=true");
        crs.setUsername("sa");
        crs.setPassword("");
        crs.setCommand("SELECT * FROM Books");
        crs.execute();
        while (crs.next()) {
            if (crs.getString("title").equals("Study in Scarlet")) {
                crs.updateInt("price", 30);
                crs.updateRow();
            }
        }
        crs.acceptChanges();
        IDataSet databaseDataSet = getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("books");
        InputStream in = getClass().getClassLoader().getResourceAsStream("database/expect/jdbc-acceptChanges.xml");
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
