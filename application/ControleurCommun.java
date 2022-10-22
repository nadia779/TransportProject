package application;

import java.io.IOException;
import java.net.URL;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.UserSession;
import projets1.ViewMap5;
import projets1.Viewmap55;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import db.Connect;

public class ControleurCommun {
	@FXML
	private static Stage stage;
	private static Scene scene;
	private static Parent root;

	public static void goBackToMain(ActionEvent e) throws IOException {
		URL location = ControleurCommun.class.getResource("Login.fxml");
		root = FXMLLoader.load(location);
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public static void goBackToAccueil(ActionEvent e) throws IOException {
		URL location = ControleurCommun.class.getResource("Accueil.fxml");
		root = FXMLLoader.load(location);
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public static void goBackToAccueil2(Event e) throws IOException {
		URL location = ControleurCommun.class.getResource("Accueil.fxml");
		root = FXMLLoader.load(location);
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public static void goBackToLignesBus(ActionEvent e) throws IOException {
		URL location = ControleurCommun.class.getResource("LignesDeBus.fxml");
		root = FXMLLoader.load(location);
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public static void goBackToLignesBus2(Event e) throws IOException {
		URL location = ControleurCommun.class.getResource("LignesDeBus.fxml");
		root = FXMLLoader.load(location);
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public static void goBackToAccueilAdmin(ActionEvent e) throws IOException {
//		if (UserSession.getRole().equals("administrateur")) {
			URL location = ControleurCommun.class.getResource("GestionVehicules.fxml");
			root = FXMLLoader.load(location);
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
//		} else {
//			System.err.println("404");
//		}
	}
	public void goToMap(Event e, int ligne, String type, String ligneTrain) throws IOException, SQLException {
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		System.setProperty("javafx.platform", "desktop");
		 
		stage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
		     @Override
		     public void handle(WindowEvent t) {
		         Platform.exit();
		         System.exit(0);
		     }
		 });
        
       Timer timer = new Timer();
       
       timer.scheduleAtFixedRate(new TimerTask() 
	   	{
	   		   public void run(){
	   		   Platform.runLater(() -> {
	   				
   				try {
   					if (ViewMap5.acCounter == 1) {
						timer.cancel();
					}
   					ViewMap5.setScene(ligne, type, ligneTrain);
   			        stage.setScene(ViewMap5.scene);
//   			        stage.setTitle("View map 5");
   			        stage.setMaximized(true);
//   			        stage.show();
//   			        Thread.sleep(2000);
   				} catch (SQLException e1) {
   					e1.printStackTrace();
   				}
	   				
				 stage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
				     @Override
				     public void handle(WindowEvent t) {
				         Platform.exit();
				         System.exit(0);
				     }
				 });
	   		});
	   		   }   
	   	 }, 0, 7000);
	}
	
	
}
