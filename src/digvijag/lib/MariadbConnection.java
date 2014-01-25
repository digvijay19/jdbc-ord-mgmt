package digvijag.lib;

import java.sql.*;

public class MariadbConnection {

    public Connection getDbConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "digvijay", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
