package application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RegisterController  {
   
	@FXML 
    private Button CloseButton  ; 
	@FXML 
	private Label registerLabel ; 
	@FXML 
	private PasswordField PasswordRegisterField ; 
	@FXML 
	private PasswordField ConfirmField ; 
	@FXML 
	private Label registerLabel2 ;
	@FXML 
	private TextField FirstField; 
	@FXML 
	private TextField LastField; 
	@FXML 
	private TextField EmailField; 
	
	public void CloseButtonOnAction(ActionEvent event) {
		Stage stage = (Stage) CloseButton.getScene().getWindow(); 
		stage.close();
//		Platform.exit();
	}
	
	public void registerButtonOnAction(ActionEvent event) {
		if(PasswordRegisterField.getText().equals(ConfirmField.getText())) {
			registerUser() ;
			registerLabel2.setText("");
			 
		}else {
			registerLabel2.setText("Password does not match!!");
		}
			
		
		
		 
	}
	
	public void registerUser() {
		Databasecnx connectnow = new Databasecnx() ; 
		Connection connectDB = connectnow.getConnection(); 
		String firstname = FirstField.getText() ; 
		String lastname = LastField.getText() ; 
		String email = EmailField.getText() ; 
		String password = PasswordRegisterField.getText() ; 
		
		String insertfield = "insert into utilisateurs(nom , prenom , email , role, password) values('" ; 
		String insertvalue =  lastname+"','" + firstname +"','"+ email +"','ordinaire','"+password + "')"; 
		String insertoregister =  insertfield +  insertvalue ;
		
		  try {
	        	Statement statement=connectDB.createStatement();
	            statement.executeUpdate(insertoregister);
	        	registerLabel.setText("User has been registred succefully");
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
		}
	}
	
   
    

