package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AccueilAdmin extends Application {
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stagePrimaire) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("GestionVehicules.fxml")) ; 	// root node
		Scene scene = new Scene(root); 	// creating the scene
		Image icon = new Image("enso.png"); 	// creating an icon
		
		stagePrimaire.getIcons().add(icon); 	// adding icon to stage
		stagePrimaire.setTitle("Projet S1 - version 0.1"); 	// adding title to stage
		stagePrimaire.setScene(scene); // adding scene to the stage
		
		stagePrimaire.show(); 	// without this we won't see no window
		
	}
}