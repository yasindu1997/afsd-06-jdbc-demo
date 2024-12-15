package lk.acpt.demofx.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;

    private final Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        //load Driver class to ram
        Class.forName("com.mysql.cj.jdbc.Driver");

        //establish a connection with db
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afsd_06", "root", "acpt");
    }

    public static DBConnection getDBConnection() throws SQLException, ClassNotFoundException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
