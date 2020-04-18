package application;
import java.sql.*;

public class SqliteConnection {
	public static Connection loginConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:EmployeeDb.db");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
