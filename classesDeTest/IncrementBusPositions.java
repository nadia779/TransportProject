package classesDeTest;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.Connect;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IncrementBusPositions {

	public static void main(String[] args) {
		Connection con = Connect.getConnection();
		try {
        	String query = "select count(1) from utilisateurs where prenom =? and password =?";
    		PreparedStatement pstmt = con.prepareStatement(query);
    		
    		pstmt.setString(1, "omar");
    		pstmt.setString(2, "1234");
        	//Statement statement=connectDB.createStatement();
        	ResultSet result = pstmt.executeQuery();
        	System.out.println(result.getRow());
        	System.out.println(result.isBeforeFirst() );
//        	if (result.getRow()==1) {    
//        		System.out.println("blan");
//        	} else {
//        		System.out.println("mochkiiil");
//        	}
        	
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
	}

}
