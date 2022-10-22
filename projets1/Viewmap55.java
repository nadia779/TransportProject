package projets1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import db.Connect;


public class Viewmap55 {
	   private static Viewmap55 view; 
	   private static Button buttonAccueil; 
	   private static Button buttonLignes;
	   private static  HBox hbox ;
	   public static WebView  webView;
	   private static WebEngine webEngine;
	   private static int setNum ;
	   public static Scene scene;
	   public static Stage stage;
	   public static BorderPane root;
	   private static String json;
	   private static String json1;
	   private static String json2;
	   
	 public  Viewmap55(int ligne) throws SQLException
	   {
 
		 this.hbox = new HBox();
		 this.buttonLignes  = new Button("Lignes");
		 this.buttonAccueil = new Button("Accueil");

		 this.hbox = new HBox(); 
		 this.hbox.getChildren().addAll(this.buttonLignes,this.buttonAccueil);
			  
		   
		 this.webView= new WebView();
		 this.webEngine = webView.getEngine();
	     loadContent();
	     
		  this.root=new BorderPane();
		  this.root.setCenter(this.webView);
		  this.root.setTop(this.hbox);
		  
			if(setNum==0)
			{
			 scene = new Scene(this.root);
//			 stage.setScene(Viewmap55.scene);
//		     stage.setTitle("View map 5");
//		     stage.show();
			 setNum=1;
			}  	  
	   }
	   
	public static void setScene(int ligne) throws SQLException
	{
		Viewmap55.view=new Viewmap55(ligne);
		System.out.println(Viewmap55.scene);
		Viewmap55.scene.setRoot(Viewmap55.view.root);
		
	}
	
	public static void loadContent() {
		Viewmap55.webEngine.loadContent("<!DOCTYPE html>\r\n" + 
     		"<html lang=\"en\">\r\n" + 
     		"<head>\r\n" + 
     		"    <meta charset=\"UTF-8\">\r\n" + 
     		"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
     		"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
     		"    <title>Document</title>\r\n" + 
     		"</head>\r\n" + 
     		"<body>\r\n" + 
     		"    <h1>WELCOME!!!!!!!</h1>\r\n" + 
     		"    <img src=\"https://images.unsplash.com/photo-1453728013993-6d66e9c9123a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8dmlld3xlbnwwfHwwfHw%3D&w=1000&q=80\" alt=\"\">\r\n" + 
     		"</body>\r\n" + 
     		"</html>");
	}
}


 
