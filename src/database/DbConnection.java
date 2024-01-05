package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String url = "jdbc:mysql://localhost:3306/clients";
    private static final String user = "root";
    private static final String password = "docker";

    private static Connection conn;

    public static Connection getConnection() {
       try {
        if (conn == null) {
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } else {
            return conn;
        }
       } catch (SQLException e) {
        e.printStackTrace();
        return null;
       }
    }
}
