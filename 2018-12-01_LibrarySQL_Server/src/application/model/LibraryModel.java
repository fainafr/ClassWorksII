package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.connection.Connection_DB;
import dto.Countries;
import dto.Publisher;

public class LibraryModel {
	
	public static Connection connection;
	
	// connection
	
	public static boolean setConnection() {
		try {
			connection = Connection_DB.getConnection();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// country
	
	public static int findCountry(String country) throws SQLException {
		String dbReq = "SELECT id_country FROM countries WHERE country_name='"+country+"'";
		ResultSet rs = connection.createStatement().executeQuery(dbReq);
		if (rs.next()) return rs.getInt(1);
		else return -1;
	}
	
	public static int addCountry(String country) throws SQLException {
		
		String dbReq = "INSERT INTO countries (country_name) VALUES ('"+country+"')";
		String[] returnedField = {"id_country"};
		PreparedStatement statement = connection.prepareStatement(dbReq, returnedField);
		statement.executeUpdate();
		
		ResultSet rs = statement.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);
	}
	
	
	public static int findOrAddCountry(String country) throws SQLException {
		
		int res = findCountry(country);
		if ( res > 0) return res;
		else return addCountry(country);
		
	}
	
	// author
	
	public static int findAuthor(String first_name, String last_name) throws SQLException {
		String dbReq = "SELECT id_author FROM authors WHERE "+
				"first_name='"+first_name+"'AND last_name='"+last_name+"'";
		ResultSet rs = connection.createStatement().executeQuery(dbReq);
		if (rs.next()) return rs.getInt(1);
		else return -1;
	}
	
	public static int addAuthor(String first_name, String last_name) throws SQLException {
		
		String dbReq = "INSERT INTO authors (first_name, last_name) VALUES ('"+
		                               first_name+"','"+last_name+"')";
		String[] returnedField = {"id_author"};
		PreparedStatement statement = connection.prepareStatement(dbReq, returnedField);
		statement.executeUpdate();
		
		ResultSet rs = statement.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);
	}
	
	public static int findOrAddAuthor(String first_name, String last_name) throws SQLException {
		
		int res = findAuthor(first_name, last_name);
		if ( res > 0) return res;
		else return addAuthor(first_name, last_name);
	}
	
	// publisher
	
	public static int[] findPublisher(String publisher_name, String country) throws SQLException {
		
		int[] result = {-1, -1};
		
		int id_country = findCountry(country);
		if (id_country < 0) return result;
		else result[1] = id_country;
		
		String dbReq = "SELECT id_publisher FROM publishers WHERE "+
				"publisher_name='"+publisher_name+"' AND id_country="+id_country;
		ResultSet rs = connection.createStatement().executeQuery(dbReq);
		if (rs.next()) result[0] = rs.getInt(1);
		return result;	
	}
	
	public static int addPublisher(String publisher_name, int id_country) throws SQLException {
		
		String dbReq = "INSERT INTO publishers (publisher_name, id_country) VALUES ('"+
		                               publisher_name+"',"+id_country+")";
		String[] returnedField = {"id_author"};
		PreparedStatement statement = connection.prepareStatement(dbReq, returnedField);
		statement.executeUpdate();
		
		ResultSet rs = statement.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);
	}
	
	public static int findOrAddPublisher(String publisher_name, String country) throws SQLException {
		int[] res = findPublisher(publisher_name, country);
		
		if (res[0] >= 0 && res[1] >= 0) return res[0];
		
		if (res[1] < 0) return addPublisher(publisher_name, addCountry(country));
		return addPublisher(publisher_name, res[1]);
	}
	
	public static Publisher getPublisherById(int id) throws SQLException {
		String dbReq = "SELECT publisher_name,country_name FROM "+
	                   "(SELECT * FROM publishers WHERE id_publisher="+id+") AS x "+
				       "JOIN countries ON x.id_country=countries.id_country";
		ResultSet rs = connection.createStatement().executeQuery(dbReq);
			
		return rs.next() ? new Publisher(Countries.valueOf(rs.getString(2)), rs.getString(1)) : null;
	}
	
	public static Iterable<Publisher> getPublishersByCountry(String country) throws SQLException {
		
		ArrayList<Publisher> result = new ArrayList<>();
		String dbReq = "SELECT publishers.publisher_name, x.country_name "
						+ "FROM publishers JOIN (SELECT * FROM countries WHERE country_name='"+country+"') AS x  "
						+ "ON publishers.id_country = x.id_country";
		ResultSet rs = connection.createStatement().executeQuery(dbReq);
		
		while(rs.next())result.add(new Publisher(Countries.valueOf(rs.getString(2)), rs.getString(1)));
		return result;
	}

}
