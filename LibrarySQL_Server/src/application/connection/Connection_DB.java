package application.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_DB {

	// DB connection parameters
	
		private static final String URL = "jdbc:mysql://localhost:3306";
		private static final String DB = "library_j2h";
		private static final String USER = "root";
		private static final String PASSWORD = "12345.com";
		private static final boolean SSL = false;
		
	// -------------------------------------
		
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL+"/"+DB+(SSL ? "" : "?useSSL=false"), USER, PASSWORD);
	}
}
