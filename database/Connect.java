package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	private static Connection con;
	private Connect() {};
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.
					getConnection("jdbc:mysql://localhost/projets1","root","nadia123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
