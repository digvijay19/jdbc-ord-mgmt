package digvijag.lib;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        statement.execute("create table DMLTest(columnOne int,columnTwo varchar(20));");
    }

    @After
    public void tearDown() throws Exception {
        statement.execute("drop table DMLTest");
        statement.close();
        mariadbConnection.closeConnection(connection);
    }

    @Test
    public void test_insert_one_row_in_table() throws SQLException {
        String query = "insert into DMLTest values(1,\"test\");";
        int expected = 1;
        int actual = statement.executeUpdate(query);
        assertEquals(expected, actual);
    }

    @Test
    public void test_insert_multiples_row_in_table() throws SQLException {
        String query1 = "insert into DMLTest values(1,\"test\");";
        String query2 = "insert into DMLTest values(2,\"test2\");";
        int[] expected = {1, 1};
        statement.addBatch(query1);
        statement.addBatch(query2);
        int[] actual = statement.executeBatch();
        assert (Arrays.equals(expected, actual));
    }

    @Test
    public void test_delete_multiples_row_in_table() throws SQLException {
        String query1 = "insert into DMLTest values(1,\"test\");";
        String query2 = "insert into DMLTest values(2,\"test2\");";
        int[] expected = {1, 1};
        statement.addBatch(query1);
        statement.addBatch(query2);
        int[] actual = statement.executeBatch();
        assert (Arrays.equals(expected, actual));
    }

    @Test
    public void test_select_from_table() throws SQLException {
        String insertQuery = "insert into DMLTest values(1,\"test\");";
        String selectQuery = "select * from DMLTest";
        String expected = "test";
        String actual = null;
        statement.executeQuery(insertQuery);
        ResultSet resultSet = statement.executeQuery(selectQuery);
        while (resultSet.next()){
            actual = resultSet.getString(2);
        }
        resultSet.close();
        assertEquals(expected, actual);
    }
}
