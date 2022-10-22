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

import application.ControleurCommun;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import db.Connect;


public class ViewMap5 {
	   public static ViewMap5 view; 
	   public static Button buttonAccueil; 
	   public static Button buttonLignes;
	   private static  HBox hbox ;
	   public  static WebView  webView;
	   public static WebEngine webEngine;
	   private static int setNum ;
	   public static Scene scene;
	   public static BorderPane root;
	   private static String json;
	   private static String json1;
	   private static String json2;
	   public static int acCounter = 0;
	   
	 public  ViewMap5(int ligne, String type, String ligneTrain) throws SQLException
	   {
	
		   ResultSet rs1= request1(ligne,type,ligneTrain);  //stations polyligne
		   this.json1 = toJSON(rs1);
		   ResultSet rs= null;
		   if (ligne != 0) rs= request(ligne,null);
		   if (ligneTrain !=null) rs= request(0,ligneTrain);//les vehicules de la ligne
		   this.json = toJSON(rs);    
		   
		   
		   if (ligne != 0) rs= request(ligne,null);
		   if (ligneTrain !=null) rs= request(0,ligneTrain);           //des donnnees IoT des vehicules
		   this.json2 = toJSON2(rs);  
		  
		  
		 this.hbox = new HBox();
		 this.buttonLignes  = new Button("Lignes");
		 this.buttonAccueil = new Button("Accueil");
		 
//		 buttonAccueil.setOnAction(new EventHandler<ActionEvent>() {
//
//				@Override
//				public void handle(ActionEvent arg0) {
//					try {
//						ControleurCommun.goBackToAccueil(arg0);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}} );
		 
		 buttonAccueil.addEventHandler(MouseEvent.MOUSE_CLICKED,
			new EventHandler<MouseEvent>(){
		        @Override
		        public void handle(MouseEvent e){
		        	try {
						ControleurCommun.goBackToAccueil2(e);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
		        	acCounter = 1;
		            System.out.println("\nMouse Entered on Click Me Two : " + acCounter);
		        }
		 });
		 buttonLignes.addEventHandler(MouseEvent.MOUSE_CLICKED,
				 new EventHandler<MouseEvent>(){
			 @Override
			 public void handle(MouseEvent e){
				 try {
					 ControleurCommun.goBackToLignesBus2(e);
				 } catch (IOException ex) {
					 ex.printStackTrace();
				 }
				 acCounter = 1;
				 System.out.println("\nMouse Entered on Click Me Two : " + acCounter);
			 }
		 });
	 
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
	     		+ "  <title>MapView</title>\r\n"
	     		+ "  <style>\r\n"
	     		+ "    html, body \r\n"
	     		+ "	{\r\n"
	     		+ "       height: 100%;\r\n"
	     		+ "       margin:0px;\r\n"
	     		+ "     }\r\n"
	     		+ "\r\n"
	     		+ "     #mapid\r\n"
	     		+ "     {\r\n"
	     		+ "       height: 100%;\r\n"
	     		+ "     }\r\n"
	     		+ "\r\n"
	     		+ "  </style>\r\n"
	     		+ "</head>\r\n"
	     		+ "<body>\r\n"
	     		+ "  <script src=\"https://leafletjs-cdn.s3.amazonaws.com/content/leaflet/master/leaflet-src.js\">\r\n"
	     		+ "		\r\n"
	     		+ "	</script>    <!--leaflet js -->\r\n"
	     		+ "  \r\n"
	     		+ "  <div id=\"mapid\">\r\n"
	     		+ "  </div>\r\n"
	     		+ "  <div   id=\"data\" hidden>\r\n"
	     		+json1+ "  </div> \r\n"
	     		+ "  \r\n"
	     		+"  <div id=\"data1\" hidden>\r\n"
	     		+json+ "  </div>\r\n"
	     		+ "  <div id=\"data2\" hidden>\r\n"
	     		+json2+ "  </div>\r\n"
	     		+ "\r\n"
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
	     		+ "  var variableRecuperee =document.getElementById(\"data\").textContent;\r\n"
	     		+ "  let dataJSON=JSON.parse(variableRecuperee);\r\n"
	     		+ "\r\n"
	     		+ "  \r\n"
	     		+ "\r\n"
	     		+ "   var latlngs = [];\r\n"
	     		+ "\r\n"
	     		+ "    \r\n"
	     		+ " for(i=0;i<dataJSON.length; i++)\r\n"
	     		+ " {\r\n"
	     		+ "     latlngs.push([parseFloat(dataJSON[i]['latitude']) ,parseFloat(dataJSON[i]['longitude'])]);\r\n"
	     		+ " }   \r\n"
	     		+ "  var polyline = L.polyline(latlngs, {color: 'green', opacity: 1, weight: 7}).addTo(mymap);\r\n"
	     		+ "  mymap.panTo(new L.LatLng(dataJSON[4]['latitude'],dataJSON[4]['longitude']));  \r\n"
	     		+ "\r\n"
	     		+ "\r\n"
	     		+ "var icone = L.icon({\r\n"
		  		+ "              iconUrl: \""+getClass().getResource("bus-stop.png")+"\","
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
	     		+ "	 variableRecuperee2 = document.getElementById(\"data2\").textContent;\r\n"
	     		+ "     dataJSON2=JSON.parse(variableRecuperee2);\r\n"
	     		+ "	 var markers=[];\r\n"
	     		+ "	 \r\n"
	     		+ "        for(let i=0;i<dataJSON1.length;i++)\r\n"
	     		+ "        {\r\n"
	     		+ "       if(markers[i])\r\n"
	     		+ "         {\r\n"
	     		+ "           mymap.removeLayer(markers[i])\r\n"
	     		+ "           markers[i] = L.marker([dataJSON1[i].latitude,dataJSON1[i].longitude],{icon: icone}).bindPopup(\"Vehicule: TEMPERATURE: \"+dataJSON2[i]['temperature']+\"  PLACES VACANTES\"+dataJSON2[i]['nbVacante']);\r\n"
	     		+ "           markers[i].addTo(mymap);\r\n"
	     		+ "         }\r\n"
	     		+ "         else \r\n"
	     		+ "          {\r\n"
	     		+ "			markers[i] = L.marker([dataJSON1[i].latitude,dataJSON1[i].longitude],{icon: icone}).bindPopup(\"Vehicule: TEMPERATURE: \"+dataJSON2[i]['temperature']+\"  PLACES VACANTES: \"+dataJSON2[i]['nbVacante']);\r\n"
	     		+ "            markers[i].addTo(mymap);\r\n"
	     		+ "          }\r\n"
	     		+ "        }\r\n"
	     		+ "\r\n"
	     		+ "		}  \r\n"
	     		+ "		doChange()\r\n"
	     		+ "      setInterval(doChange,5000);\r\n"
	     		+ "\r\n"
	     		+ "\r\n"
	     		+ "</script>\r\n"
	     		+ "  </body>\r\n"
	     		+ "</html>");
			  this.root=new BorderPane();
			  this.root.setCenter(this.webView);
			  this.root.setTop(this.hbox);
			if(setNum==0)
			{
			 scene = new Scene(this.root);
			 setNum=1;
			}  	  
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

	public static void setScene(int ligne, String type, String ligneTrain) throws SQLException
	{
		ViewMap5.view=new ViewMap5(ligne, type, ligneTrain);
		System.out.println(ViewMap5.scene);
		ViewMap5.scene.setRoot(ViewMap5.view.root);
		
	}
	public static void reload() {
		webEngine.reload();
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
			System.out.println(json);
		return json;
	}
	
	public static String toJSON2(ResultSet rs) throws SQLException
	{
		 String json="[";
		 while(rs.next())  
			{  
	    	 String t="{\"temperature\":\""+rs.getFloat(5)+"\",\"nbVacante\":\""+rs.getInt(10)+"\"},";
			 json = json +t;  
			}
	        json = json.substring(0, json.length()-1);
			json = json +"]";
			System.out.println(json);
		return json;
	}
	
	
	
	public static ResultSet request(int ligne, String ligneTrain) throws SQLException 
	{
		 Connection con = Connect.getConnection();
		 Statement stmt = con.createStatement();
		 ResultSet rs = null;
		 System.out.println(" reqmethod : " + ligne +"--"+ ligneTrain);
		 if (ligne != 0) {
			 rs = stmt.executeQuery("select * from vehicules inner join stations_lignes on vehicules.ligneBus=stations_lignes.idStation"
			 		+ " where ligneBus = " + ligne);
			 
		 }
		 if (ligneTrain != null) {
			 String query = "select * from vehicules inner join station_lignes_train on vehicules.nomTrain=station_lignes_train.idStation"
				 		+ " where nomTrain = '" + ligneTrain + "'";
			 System.out.println("this is req query (rsem trajet): " + query);
			 rs = stmt.executeQuery(query);
		 }
	return rs;
	}
	
	public static ResultSet request1(int ligne, String type, String ligneTrain) throws SQLException 
	{
		 Connection con = Connect.getConnection();
		 Statement stmt=con.createStatement();
		 ResultSet rs;
		 System.out.println(" reqmethod1 : " + ligne +"--"+ type);
		 if (type.equals("bus")) {
			 // where ligne = ?    ghadir hna if(type==bus) stations_lignes_bus ...... 
			 rs=stmt.executeQuery("select * from stations inner join stations_lignes on stations.id=stations_lignes.idStation"
					 + " where ligne = " + ligne);
		 } else {
			 String query = "select * from stations inner join station_lignes_train on stations.id=station_lignes_train.idStation"
						+ " where ligne = '" + ligneTrain + "'";
			 System.out.println("this is req1 query : " + query);
			 rs= stmt.executeQuery(query);
		 }
		return rs; 
	}
//	
// @Override
// public void start(Stage primaryStage) throws IOException, SQLException {
//	    System.setProperty("javafx.platform", "desktop");
//		 
//		 primaryStage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
//		     @Override
//		     public void handle(WindowEvent t) {
//		         Platform.exit();
//		         System.exit(0);
//		     }
//		 });
//		 
//        Timer timer = new Timer();
//        
//        timer.scheduleAtFixedRate(new TimerTask() 
//    	{
//    		   public void run(){
//    		   Platform.runLater(() -> {
//    				
//    				try {
//    			           ViewMap5.setScene();
//    			   		   primaryStage.setScene(ViewMap5.scene);
//    			   	       primaryStage.show();
//    				} catch (SQLException e1) {
//    					e1.printStackTrace();
//    				}
//    				
//    				 primaryStage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
//    				     @Override
//    				     public void handle(WindowEvent t) {
//    				         Platform.exit();
//    				         System.exit(0);
//    				     }
//    				   
//    				 });
//    		});
//    		   }   
//    	 }, 0, 15000);
//			   
//}
// 
 public static void main(String[] args)
 {
//    launch();
   
 }
 
}


 
