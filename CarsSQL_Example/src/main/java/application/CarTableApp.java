package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Connection_DB;
import dto.Car;

public class CarTableApp {

	private static final int NUM_CAR = 1000;

	public static void main(String[] args) throws SQLException {

		Connection connection = Connection_DB.getConnection();
		
		String[] sArr = new String[NUM_CAR];
		
		for (int i = 0; i < NUM_CAR; i ++){
			sArr[i] = Car.getRandomCar().toInsertString();
		}
		
		String dbDel = "DELETE FROM car_table";
		System.out.println(connection.createStatement().executeUpdate(dbDel));
		
		String dbReq = "INSERT INTO car_table (model, year, engine, ac) VALUES " + 
		String.join(",", sArr);
	    System.out.println(connection.createStatement().executeUpdate(dbReq));
		
		String dbCount = "SELECT COUNT(*) FROM car_table;";
		System.out.println("COUNT");
		printIntResult(connection, dbCount);
		
		String dbAvg = "SELECT AVG(engine) FROM car_table;";
		System.out.println("AVERAGE ENGINE");
		printDoubleResult(connection, dbAvg);
		
		String dbGroup = "SELECT count(*), ac FROM car_table GROUP BY ac;";
		System.out.println("AC AND NOT AC");
		printTableResults(connection, dbGroup, 1);
		
		String dbOrder = "SELECT * FROM car_table ORDER BY year,model,engine";
		System.out.println("ORDERED");
		printTableResults(connection, dbOrder, 2);
		
		String dbMinBoundary = "SELECT count(*) FROM car_table WHERE engine < (SELECT avg(engine) FROM car_table);";
		System.out.println("LESS THAN AVG");
		printTableResults(connection, dbMinBoundary, 1);
		
		String dbMaxBoundary = "SELECT count(*) FROM car_table WHERE engine > (SELECT avg(engine) FROM car_table);";
		System.out.println("MORE THAN AVG");
		printTableResults(connection, dbMaxBoundary, 1);
		
		
		connection.close();
	}

	private static void printIntResult(Connection connection, String sql) throws SQLException {
		ResultSet rs = connection.createStatement().executeQuery(sql);
		rs.next();
		System.out.println(rs.getInt(1));
	}
	
	private static void printDoubleResult(Connection connection, String sql) throws SQLException {
		ResultSet rs = connection.createStatement().executeQuery(sql);
		rs.next();
		System.out.println(rs.getDouble(1));
	}	
	
	private static void printTableResults(Connection connection, String sql, int Columns) throws SQLException {
		ResultSet rs = connection.createStatement().executeQuery(sql);
		while(rs.next()){
			for(int i = 1; i <= Columns; i++){
			System.out.print(rs.getString(i)+" ");
			}
			System.out.println();
		}
	}
	

}
