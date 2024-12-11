package Prova_A2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/empregos";
    private static final String USER = "root";
    private static final String PASSWORD = "Matheus2005";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
