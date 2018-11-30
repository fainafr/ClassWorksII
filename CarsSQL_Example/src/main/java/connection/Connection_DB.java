package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Connection_DB {

	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String DB = "java2h_cars";
	private static final String USER = "root";
	private static final String PASSWORD = "12345.com";
	private static final boolean SSL = false; 
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL+"/"+DB+(SSL ? "" : "?useSSL=false"), USER, PASSWORD);
	};
	
	public static ResultSet query(Connection connection, String queryString) throws SQLException {
		return connection.createStatement().executeQuery(queryString);
	}
	
	public static int update(Connection connection,String queryString) throws SQLException {
		return connection.createStatement().executeUpdate(queryString);
	}
	
	public static String wrapString(String str) {return "'"+str+"'";}
	
}
