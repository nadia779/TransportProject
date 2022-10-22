package application;
	
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage)  {
		
			/*BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root,520,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login"); 
			primaryStage.show();*/
		try {
			URL location = getClass().getResource("Login.fxml");
			Parent root = FXMLLoader.load(location);
			//Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			 Scene scene = new Scene(root);
			 Image icon = new Image("enso.png"); 	// creating an icon
			 
			 primaryStage.getIcons().add(icon); 	// adding icon to stage
			 primaryStage.setTitle("Projet S1 - version 0.1");
			 primaryStage.setScene(scene);
			 primaryStage.show();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

 
