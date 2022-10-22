package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import projets1.Viewmap55;

public class ControleurMap {
	@FXML
	private static Stage stage;
	private static Scene scene;
	private static Parent root;
	
	public void goToMap(Event e, int ligne) throws IOException, SQLException {
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		System.setProperty("javafx.platform", "desktop");
		 
		stage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
		     @Override
		     public void handle(WindowEvent t) {
		         Platform.exit();
		         System.exit(0);
		     }
		 });
		Viewmap55.setScene(ligne);
        stage.setScene(Viewmap55.scene);
        stage.setTitle("View map 5");
        stage.show();
        
//        System.out.println(Viewmap55.scene.getRoot().get);
        
       Timer timer = new Timer();
       
       timer.scheduleAtFixedRate(new TimerTask() 
	   	{
	   		   public void run(){
	   		   Platform.runLater(() -> {
	   				
   				try {
//   					Viewmap55.setScene(ligne);
//   			        stage.setScene(Viewmap55.scene);
//   			        stage.setTitle("View map 5");
//   			        stage.show();
   					Viewmap55.root.getChildren().remove(Viewmap55.webView) ;
   					Viewmap55.root.getChildren().add(Viewmap55.webView) ;
   			        System.out.println(Viewmap55.scene.getRoot());
   				} catch (Exception e1) {
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
