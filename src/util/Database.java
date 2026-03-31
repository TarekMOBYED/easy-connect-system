package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {

	  private static final String URL = "jdbc:mysql://localhost:3306/easy_connect";
	    private static final String USER = "root";
	    private static final String PASSWORD = "1234";

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}