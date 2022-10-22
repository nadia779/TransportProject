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


public class ViewMap4 extends Application {
	   private static ViewMap4 view; 
	   private static Button buttonAccueil; 
	   private static Button buttonLignes;
	   private static  HBox hbox ;
	   private static WebView  webView;
	   private static WebEngine webEngine;
	   private static Scene scene;
	   private static BorderPane root;
	   private static String json;
	   private static String json1;
	   
	  public  ViewMap4() throws SQLException
	   {
		   ResultSet rs1= request1();  //stations polyligne
		   this.json1 = toJSON(rs1);
		   
		   ResultSet rs= request();    //les vehicules de la ligne
		   this.json = toJSON(rs);      
		   
	
		  
		  
		 this.hbox = new HBox();
		 this.buttonLignes  = new Button("Lignes");
		 this.buttonAccueil = new Button("Accueil");
		 this.hbox = new HBox(); 
		 this.hbox.getChildren().addAll(this.buttonLignes,this.buttonAccueil);
			  
		   
		 this.webView= new WebView();
		 this.webEngine = webView.getEngine();
	     this.webEngine.loadContent("<!DOCTYPE html>\r\n"
				  		+ "<html lang=\\\\\"en\\\\\">\r\n"
				  		+ "<head>\r\n"
				  		+ "  <meta charset=\\\\\"UTF-8\\\\\">\r\n"
				  		+ "  <meta http-equiv=\\\\\"X-UA-Compatible\\\\\" content=\\\\\"IE=edge\\\\\">\r\n"
				  		+ "  <meta name=\\\\\"viewport\\\\\" content=\\\\\"width=device-width, initial-scale=1.0\\\\\">\r\n"
				  		+ "\r\n"
				  		+ "  <link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet@1.7.1/dist/leaflet.css\"\r\n"
				  		+ "\r\n"
				  		+ "  integrity=\"sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A==\"\r\n"
				  		+ "\r\n"
				  		+ "  crossorigin=\"\"/>\r\n"
				  		+ "\r\n"
				  		+ "  <title>Document</title>\r\n"
				  		+ "  <style>\r\n"
				  		+ "    html, body {\r\n"
				  		+ "  height: 100%;\r\n"
				  		+ "}\r\n"
				  		+ "\r\n"
				  		+ "#mapid {\r\n"
				  		+ "  height: 100%;\r\n"
				  		+ "}\r\n"
				  		+ "/*\r\n"
				  		+ "    #mapid { \r\n"
				  		+ "      width:100vh;\r\n"
				  		+ "      height: 100vh;\r\n"
				  		+ "      /*height: 500px*/\r\n"
				  		+ "  </style>\r\n"
				  		+ "</head>\r\n"
				  		+ "<body>\r\n"
				  		+ "  <script src=\"https://leafletjs-cdn.s3.amazonaws.com/content/leaflet/master/leaflet-src.js\">\r\n"
				  		+ "		\r\n"
				  		+ "	</script>    <!--leaflet js -->\r\n"
				  		+ "  \r\n"
				  		+ "  <div id=\"mapid\">\r\n"
				  		+ "  </div>\r\n"
				  		+ "  <script>\r\n"
				  		+ "    var mymap = L.map('mapid').setView([34.02220811162826, -6.834853410245987], 13);\r\n"
				  		+ "  \r\n"
				  		+ "          \r\n"
				  		+ "  \r\n"
				  		+ "   L.tileLayer('https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png', {\r\n"
				  		+ "  \r\n"
				  		+ "              // Il est toujours bien de laisser le lien vers la source des données\r\n"
				  		+ "  \r\n"
				  		+ "              attribution: 'données © <a href=\"//osm.org/copyright\">OpenStreetMap</a>/ODbL - rendu <a href=\"//openstreetmap.fr\">OSM France</a>',\r\n"
				  		+ "  \r\n"
				  		+ "              minZoom: 1,\r\n"
				  		+ "  \r\n"
				  		+ "              maxZoom: 20\r\n"
				  		+ "  \r\n"
				  		+ "          }).addTo(mymap);\r\n"
				  		+ "   \r\n"
				  		+ "  </script>\r\n"
				  		+ "\r\n"
				  		+ "\r\n"
				  		+ " <div   id=\"data\" hidden>\r\n"
				  		+json1+ "</div> \r\n"
				  		+ "\r\n"
				  		+ "<div id=\"data1\" hidden>\r\n"
				  		+json+ "</div>\r\n"
				  		+ "\r\n"
				  		+ "  <script> \r\n"
				  		+ "\r\n"
				  		+ "\r\n"
				  		+ "  var variableRecuperee =document.getElementById(\"data\").textContent;\r\n"
				  		+ "  let dataJSON=JSON.parse(variableRecuperee);\r\n"
				  		+ "\r\n"
				  		+ "  //document.write(variableRecuperee);\r\n"
				  		+ "  \r\n"
				  		+ "\r\n"
				  		+ "   var latlngs = [];\r\n"
				  		+ "\r\n"
				  		+ "    \r\n"
				  		+ " for(i=0;i<dataJSON.length; i++)\r\n"
				  		+ "     latlngs.push([parseFloat(dataJSON[i]['latitude']) ,parseFloat(dataJSON[i]['longitude'])]);\r\n"
				  		+ "\r\n"
				  		+ "  var polyline = L.polyline(latlngs, {color: 'green', opacity: 1, weight: 7}).addTo(mymap);\r\n"
				  		+ "\r\n"
				  		+"mymap.panTo(new L.LatLng(dataJSON[4]['latitude'],dataJSON[4]['longitude']));  "
				  		+ "\r\n"
				  		+ "var icone = L.icon({\r\n"
				  		+ "              iconUrl: \""+getClass().getResource("client.png")+"\","
				  		+ "              iconSize: [50, 50],\r\n"
				  		+ "              iconAnchor: [25, 50],                //icone pour le client\r\n"
				  		+ "              popupAnchor: [0, -50]\r\n"
				  		+ "          });\r\n"
				  		+ "var marker;\r\n"
				  		+ "var circle;\r\n"
				  		+ "\r\n"
				  		+ "        \r\n"
				  		+ "var variableRecuperee1;\r\n"
				  		+ "var dataJSON1;\r\n"
				  		+ "  let doChange = function() {\r\n"
				  		+ "     console.log(\"1\")\r\n"
				  		+ "     variableRecuperee1 = document.getElementById(\"data1\").textContent;\r\n"
				  		+ "     dataJSON1=JSON.parse(variableRecuperee1);\r\n"
				  		+ "        for(vehicule in dataJSON1)\r\n"
				  		+ "        {\r\n"
				  		+ "       \r\n"
				  		+ "    // let distance = L.latLng([dataJSON1[vehicule].latitude,dataJSON1[vehicule].longitude]).distanceTo([e1.latitude, e1.longitude]);\r\n"
				  		+ "     //console.log(distance);\r\n"
				  		+ "      \r\n"
				  		+ "      \r\n"
				  		+ "       if(dataJSON1[vehicule])\r\n"
				  		+ "         {\r\n"
				  		+ "           mymap.removeLayer(dataJSON1[vehicule])\r\n"
				  		+ "           dataJSON1[vehicule] = L.marker([dataJSON1[vehicule].latitude,dataJSON1[vehicule].longitude],{icon: icone}).bindPopup(\"Vehicule\");\r\n"
				  		+ "           dataJSON1[vehicule].addTo(mymap);\r\n"
				  		+ "          \r\n"
				  		+ "         }\r\n"
				  		+ "         else \r\n"
				  		+ "          {\r\n"
				  		+ "            dataJSON1[vehicule] = L.marker([dataJSON1[vehicule].latitude,dataJSON1[vehicule].longitude],{icon: icone}).bindPopup(\"Vehicule\");\r\n"
				  		+ "            dataJSON1[vehicule].addTo(mymap);\r\n"
				  		+ "                                          // récuperer  les cordonnees des taxis et les afficher \r\n"
				  		+ "          }\r\n"
				  		+ "        } \r\n"
				  		+ "      }\r\n"
				  		+ "      doChange();\r\n"
				  		+ "     // setInterval(doChange,5000);\r\n"
				  		+ "\r\n"
				  		+ "\r\n"
				  		+ "      \r\n"
				  		+ "\r\n"
				  		+ "\r\n"
				  		+ "</script>\r\n"
				  		+ "  </body>\r\n"
				  		+ "</html>");
	     
			  this.root=new BorderPane();
			  this.root.setCenter(this.webView);
			  this.root.setTop(this.hbox);
			
			  this.scene = new Scene(this.root);
			  	  
	   }
	   
	   
	public static String fileToString(String filename) throws IOException
	{
	    BufferedReader reader = new BufferedReader(new FileReader(filename));
	    StringBuilder builder = new StringBuilder();
	    String line;    

	    //For every line in the file, append it to the string builder
	    while((line = reader.readLine()) != null)
	    {
	        builder.append(line);
	    }

	    reader.close();
	    return builder.toString();
	}

	public static void setScene() throws SQLException
	{
		ViewMap4.view=new ViewMap4();
		System.out.println(ViewMap4.scene);
		ViewMap4.scene.setRoot(ViewMap4.view.root);
		
	}
	public static String toJSON(ResultSet rs) throws SQLException
	{
		 String json="[";
		 while(rs.next())  
			{  
	    	 String t="{\"latitude\":\""+rs.getBigDecimal(2)+"\",\"longitude\":\""+rs.getBigDecimal(3)+"\"},";
			 json = json +t;  
			}
	        json = json.substring(0, json.length()-1);
			json = json +"]";
			//System.out.println(json);
		return json;
	}
	
	
	public static ResultSet request() throws SQLException 
	{
		 Connection con = Connect.getConnection();
		 Statement stmt = con.createStatement(); 	
		 ResultSet rs = stmt.executeQuery("select * from vehicules inner join stations_lignes on vehicules.ligneBus=stations_lignes.idStation");
	return rs;
	}
	
	public static ResultSet request1() throws SQLException 
	{
		 Connection con = Connect.getConnection();
		 Statement stmt=con.createStatement();       // where ligne = ? 
	     ResultSet rs=stmt.executeQuery("select * from stations inner join stations_lignes on stations.id=stations_lignes.idStation ");
		return rs; 
	}
	
 @Override
 public void start(Stage primaryStage) throws IOException, SQLException {
	    System.setProperty("javafx.platform", "desktop");
	  
		System.out.println(ViewMap4.scene);
		 
		 primaryStage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
		     @Override
		     public void handle(WindowEvent t) {
		         Platform.exit();
		         System.exit(0);
		     }
		   
		 });
		 
		view.buttonAccueil.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						System.out.println("retour à l'accueil ");
				
					}} );
			  
		view.buttonLignes.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							System.out.println("retour au tableau des lignes ");
							
						}} );
				  
	    primaryStage.show();
	    
        Timer timer = new Timer();
        
        timer.scheduleAtFixedRate(new TimerTask() 
    	{
    		   public void run(){
    		   Platform.runLater(() -> {
    				
    				try {
    			           ViewMap4.setScene();
    			   		   primaryStage.setScene(ViewMap4.scene);
    				} catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    				
    				 primaryStage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
    				     @Override
    				     public void handle(WindowEvent t) {
    				         Platform.exit();
    				         System.exit(0);
    				     }
    				   
    				 });
    		});
    		   }   
    	 }, 0, 15000);
			   
}
 
 public static void main(String[] args)
 {
    launch();
   
 }
 
}


 
