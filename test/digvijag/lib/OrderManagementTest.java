package digvijag.lib;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderManagementTest {
    MariadbConnection mariadbConnection = new MariadbConnection();
    Connection connection;
    ResultSet rs;
    Statement statement;

    @Before
    public void setUp() throws Exception {
        connection = mariadbConnection.getDbConnection();
        statement = connection.createStatement();
        rs = null;
    }

    @After
    public void tearDown() throws Exception {
        rs.close();
        statement.close();
        mariadbConnection.closeConnection(connection);
    }

    @Test
    public void test_select_products_from_order_management() throws SQLException {
        rs = statement.executeQuery("select * from products");
        Assert.assertNotNull(rs);
    }
}
