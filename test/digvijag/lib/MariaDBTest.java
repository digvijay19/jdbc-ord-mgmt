package digvijag.lib;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

public class MariaDBTest {
    MariadbConnection mariadbConnection = new MariadbConnection();
    Connection connection;
    Statement statement;

    @Before
    public void setUp() throws Exception {
        connection = mariadbConnection.getDbConnection();
        statement = connection.createStatement();
        statement.execute("create table DDLTest(columnOne int,columnTwo varchar(20));");
    }

    @After
    public void tearDown() throws Exception {
        statement.execute("drop table DDLTest");
        statement.close();
        mariadbConnection.closeConnection(connection);
    }

    @Test
    public void test_insert_one_row_in_table() throws SQLException {
        String query = "insert into DDLTest values(1,\"test\");";
        int expected = 1;
        int actual = statement.executeUpdate(query);
        assertEquals(expected, actual);
    }

    @Test
    public void test_insert_multiples_row_in_table() throws SQLException {
        String query1 = "insert into DDLTest values(1,\"test\");";
        String query2 = "insert into DDLTest values(2,\"test2\");";
        int[] expected = {1, 1};
        statement.addBatch(query1);
        statement.addBatch(query2);
        int[] actual = statement.executeBatch();
        assert(Arrays.equals(expected, actual));
    }
}
