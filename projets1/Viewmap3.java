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

public class Viewmap3 extends Application {
	   private Viewmap3 view;
	   private Scene scene;
	   private BorderPane root;
	   private String json;
	   private String json1;
	   
	  public  Viewmap3() throws SQLException
	   {
		  ResultSet rs1= this.request1();  //stations polyligne
		   String json1 = this.toJSON(rs1);
		   
		   ResultSet rs= this.request();
		   String json = this.toJSON(rs);      //les vehicules de la ligne
		   
		  BorderPane root = new BorderPane();
		  HBox hbox = new HBox();
		  hbox.setStyle("-fx-display:flex;\r\n"
		  		+ "          -fx-flex-direction:row;"
		  		+ "  -fx-width:100%; ");
		  Button buttonLignes = new Button("Lignes");
		  Button buttonAccueil = new Button("Accueil");
		      buttonLignes.setStyle(" -fx-background-color:#8ee08e;\r\n"
		    		+ "        -fx-height:50px;\r\n"
		    		+ "         -fx-width:50%;\r\n"
		    		+ "         -fx-border-radius: 30px;\r\n"
		    		+ "         -fx-text-align: center;\r\n"
		    		+ "         -fx-font-size: 20px;");
		    buttonAccueil.setStyle(" -fx-background-color:#0000ff\r\n"
		    		+ ";\r\n"
		    		+ "   -fx-font-size: 20px;\r\n"
		    		+ "            -fx-height:50px;\r\n"
		    		+ "    -fx-width:50%;\r\n"
		    		+ "    -fx-border-radius: 30px;\r\n"
		    		+ "    -fx-text-align: center;");
		    
		    buttonAccueil.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					System.out.println("retour à l'accueil ");
					
				}} );
			  buttonLignes.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						System.out.println("retour au tableau des lignes ");
						
					}} );
		   hbox.getChildren().addAll(buttonLignes,buttonAccueil);
		   WebView webView = new WebView();
			  WebEngine webEngine = webView.getEngine();
			  webEngine.loadContent("<!DOCTYPE html>\r\n"
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
			  root.setCenter(webView);
			  root.setTop(hbox);
			  this.root=root;
			  this.scene=new Scene(root);
                
			  
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

	
	public String toJSON(ResultSet rs) throws SQLException
	{
		 String json="[";
		 while(rs.next())  
			{  
	    	 String t="{\"latitude\":\""+rs.getBigDecimal(2)+"\",\"longitude\":\""+rs.getBigDecimal(3)+"\"},";
			 json = json +t;  
			}
	        json = json.substring(0, json.length()-1);
			json = json +"]";
			System.out.println(json);
		return json;
	}
	
	
	public ResultSet request() throws SQLException 
	{
		 Connection con = Connect.getConnection();
		 Statement stmt = con.createStatement(); 	
		 ResultSet rs = stmt.executeQuery("select * from vehicules inner join stations_lignes on vehicules.ligneBus=stations_lignes.idStation");
	return rs;
	}
	
	public ResultSet request1() throws SQLException 
	{
		 Connection con = Connect.getConnection();
		 Statement stmt=con.createStatement();       // where ligne = ? 
	     ResultSet rs=stmt.executeQuery("select * from stations inner join stations_lignes on stations.id=stations_lignes.idStation ");
		return rs; 
	}
	
 @Override
 public void start(Stage primaryStage) throws IOException, SQLException {
	 System.setProperty("javafx.platform", "desktop");
		Viewmap3 view = new Viewmap3();
	    primaryStage.show();
  Timer timer = new Timer();

	 timer.scheduleAtFixedRate(new TimerTask() 
	{
		   public void run(){
		   Platform.runLater(() -> {
				
				try {
					view.view=new Viewmap3();
					  primaryStage.setScene(view.view.scene);
					  System.out.println("flash");
					//  primaryStage.show();
				} catch (SQLException e1) {
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
	 }, 0, 5000);
			
	


	
       
}
 
 public static void main(String[] args)
 {
    launch();
   
 }



 
}