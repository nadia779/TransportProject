package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class Databasecnx {
	public Connection databaseLink ; 
	
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/projets1" ; 
		String username ="root" ; 
		String password = "1234" ; 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink=DriverManager.getConnection(url , username , password ) ;
		}catch(Exception e ) {
			e.printStackTrace();
			e.getCause() ; 
		}
		return databaseLink ; 
		
	}
}

