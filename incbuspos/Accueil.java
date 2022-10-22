package incbuspos;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		Parent root = FXMLLoader.load(getClass().getResource("IncrementBusPositions.fxml")) ; 	// root node
		Scene scene = new Scene(root); 	// creating the scene
		Image icon = new Image("enso.png"); 	// creating an icon
		
		stagePrimaire.getIcons().add(icon); 	// adding icon to stage
		stagePrimaire.setTitle("IncrementBusPositions - Dummy App v0.1"); 	// adding title to stage
		stagePrimaire.setScene(scene); // adding scene to the stage
		
		stagePrimaire.show(); 	// without this we won't see no window
		
	}
	
	
}
