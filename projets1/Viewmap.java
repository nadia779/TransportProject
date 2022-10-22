package projets1;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Viewmap extends Application 
 {
	
	public void start(Stage primaryStage) throws SQLException
	 {
	    String json ="[";
		System.setProperty("javafx.platform", "desktop");
		
		 Connect c = new Connect();
		 Connection con = c.getConnection();
		 Statement stmt=con.createStatement();       // where ligne = ? 
	     ResultSet rs=stmt.executeQuery("select * from stations inner join stations_lignes on stations.id=stations_lignes.idStation "); 
	     while(rs.next())  
			{  
	    	 String t="{\"latitude\":\""+rs.getBigDecimal(2)+"\",\"longitude\":\""+rs.getBigDecimal(3)+"\"},";
			 json = json +t;  
			} 
        json = json.substring(0, json.length()-1);
		json = json +"]";
		System.out.println("voila"+json);

		
		
		  /* Création de la WebView et du moteur */
		 WebView webView = new WebView();
		 WebEngine webEngine = webView.getEngine();

		  /* Charge la carte HTML avec Leafletjs */
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
		  		+ "    #mapid { height: 500px; }\r\n"
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
		  		+ "    var mymap = L.map('mapid').setView([34.02220811162826, -6.834853410245987], 14);\r\n"
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
		  		+ " <div   id=\"data\" hidden> \r\n"
		  		+json+"</div> \r\n"
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
		  		+ "\r\n"
		  		+ "var icone = L.icon({\r\n"
		  		+ "              iconUrl: \""+getClass().getResource("client.png")+"\",\r\n"
		  		+ "              iconSize: [50, 50],\r\n"
		  		+ "              iconAnchor: [25, 50],                //icone pour le client\r\n"
		  		+ "              popupAnchor: [0, -50]\r\n"
		  		+ "          });\r\n"
		  		+ "var marker;\r\n"
		  		+ "var circle;\r\n"
		  		+ "var inc=0;\r\n"
		  		+ "var refreshIntervalId;\r\n"
		  		+ "\r\n"
		  		+ "\r\n"
		  		+ "\r\n"
		  		+ "\r\n"
		  		+ "\r\n"
		  		+ "\r\n"
		  		+ "let doChange = function() {\r\n"
		  		+ "   console.log(latlngs[inc]);\r\n"
		  		+ "  if(marker)\r\n"
		  		+ "          { \r\n"
		  		+ "            mymap.removeLayer(marker);\r\n"
		  		+ "            mymap.removeLayer(circle);\r\n"
		  		+ "            marker = L.marker([latlngs[inc][0],latlngs[inc][1]],{icon: icone}).bindPopup(\"<p>passager </p>\");\r\n"
		  		+ "            marker.addTo(mymap);\r\n"
		  		+ "            mymap.panTo(new L.LatLng(latlngs[inc][0],latlngs[inc][1]));\r\n"
		  		+ "            circle = L.circle([latlngs[inc][0], latlngs[inc][1]], {\r\n"
		  		+ "              color: 'blue',\r\n"
		  		+ "              fillColor: '#008bff',\r\n"
		  		+ "               fillOpacity: 0.1,\r\n"
		  		+ "               radius: 300\r\n"
		  		+ "              }).addTo(mymap);\r\n"
		  		+ "             }\r\n"
		  		+ "          else\r\n"
		  		+ "          {\r\n"
		  		+ "          mymap.panTo(new L.LatLng(latlngs[inc][0],latlngs[inc][1]));\r\n"
		  		+ "           marker = L.marker([latlngs[inc][0],latlngs[inc][1]],{icon: icone}).bindPopup(\"<p>passager </p>\");\r\n"
		  		+ "           marker.addTo(mymap);\r\n"
		  		+ "           console.log(marker);\r\n"
		  		+ "           circle = L.circle([latlngs[inc][0],latlngs[inc][1]], {\r\n"
		  		+ "              color: 'blue',\r\n"
		  		+ "              fillColor: '#008bff',\r\n"
		  		+ "               fillOpacity: 0.1,\r\n"
		  		+ "               radius: 300\r\n"
		  		+ "              }).addTo(mymap);\r\n"
		  		+ "          }\r\n"
		  		+ "          inc++;\r\n"
		  		+ "          if(inc>latlngs.length-1)\r\n"
		  		+ "           {\r\n"
		  		+ "            clearInterval(refreshIntervalId);\r\n"
		  		+ "           }\r\n"
		  		+ "           else \r\n"
		  		+ "           {\r\n"
		  		+ "             console.log(\"nice\");\r\n"
		  		+ "           }\r\n"
		  		+ "        }\r\n"
		  		+ "        doChange();\r\n"
		  		+ "        refreshIntervalId = setInterval(doChange,5000)\r\n"
		  		+ "     \r\n"
		  		+ "\r\n"
		  		+ "</script>\r\n"
		  		+ "  </body>\r\n"
		  		+ "</html>");
        
		Scene scene = new Scene(webView, 400, 300);
		primaryStage.setTitle("suivi de transport");
		primaryStage.setScene(scene);
		primaryStage.show();	
	 }
	
	 public static void main(String[] args) 
     {
		launch();
		
     }
}
	
	

