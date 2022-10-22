package application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
/*import javafx.scene.image.Image;
import javafx.scene.image.ImageView;*/
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.UserSession;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class LoginController implements Initializable {
	@FXML 
    private Button CancelButton  ; 
	@FXML
	private Label LoginMessageLabel ; 
	@FXML 
	private TextField usernameTextField ; 
	@FXML 
	private PasswordField passwordTextField ; 
	
	
	
	@Override 
	public void initialize(URL url , ResourceBundle resourcebundle) {
		/*File transportFile = new File("Images/transportation-icon-public-transport.png");
		Image transportImage = new Image(transportFile.toURI().toString()); 
		transportImageview.setImage(transportImage); */
		
		
	}
	public void CancelButtonOnAction(ActionEvent event) {
		Stage stage = (Stage) CancelButton.getScene().getWindow(); 
		stage.close();
	}
	
	public void LoginButtonOnAction(ActionEvent event) {
		
		if(usernameTextField.getText().isBlank()==false && passwordTextField .getText().isBlank()==false ) {
			validateLogin(event ); 
		}else {
			LoginMessageLabel.setText("Enter your information!");
			
		}
	}
	public void validateLogin(ActionEvent e ) {
		Databasecnx connectnow = new Databasecnx() ; 
		Connection connectDB = connectnow.getConnection(); 
		//LoginMessageLabel.setText("You're trying to login");
		/*String verifylogin = "select * from utilisateurs where prenom = " +
		usernameTextField.getText() + "And password ="+passwordTextField.getText() ;*/
		
		
		/*System.out.println(passwordTextField.getText()); 
		System.out.println(usernameTextField.getText()); */
        try {
        	String query = "select * from utilisateurs where email =? and password =?";
    		PreparedStatement pstmt = connectDB.prepareStatement(query);
    		
    		pstmt.setString(1, usernameTextField.getText());
    		pstmt.setString(2, passwordTextField.getText());
        	//Statement statement=connectDB.createStatement();
        	ResultSet result = pstmt.executeQuery();
        	//System.out.println(result.getRow());
        	if (result.isBeforeFirst()) {
        		URL location = null;
        		result.next();
        		String role = result.getString("role");
        		if (role.equals("administrateur")) location = getClass().getResource("GestionVehicules.fxml");
        		if (role.equals("ordinaire")) location = getClass().getResource("Accueil.fxml");
        		UserSession session = UserSession.getInstance(role);
        		Parent root = FXMLLoader.load(location);
        		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        		 Scene scene = new Scene(root);
        		stage.setScene(scene);
        		stage.show();
        	} else {
        		LoginMessageLabel.setText("Invalid Login Try again!!"); 
        	}
        	
        	/*while(result.next()) {
        		if(result.getInt(1)==1) {
        			 //LoginMessageLabel.setText("Congrats you're conected"); 
        			//createAccount() ; 
        		}else {
        			LoginMessageLabel.setText("Invalid Login Try again!!"); 
        		}
        	}*/
        }catch(Exception ex) {
        	ex.printStackTrace();
        }
	}
	
	public void createAccount() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
			Stage registerStage = new Stage() ; 
			//registerStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			registerStage.setScene(scene);
			 registerStage.setTitle("Register");;
		    registerStage.show();
		    
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause(); 
		}
	}
	
}
