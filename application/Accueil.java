package application;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import models.UserSession;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Accueil extends Application {
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stagePrimaire) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml")) ; 	// root node
		Scene scene = new Scene(root); 	// creating the scene
		Image icon = new Image("enso.png"); 	// creating an icon
		
		stagePrimaire.getIcons().add(icon); 	// adding icon to stage
		stagePrimaire.setTitle("Projet S1 - version 0.1"); 	// adding title to stage
		stagePrimaire.setScene(scene); // adding scene to the stage
		
		stagePrimaire.show(); 	// without this we won't see no window
		
	}
}
