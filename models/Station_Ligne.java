package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;

public class Station_Ligne {
	private IntegerProperty id;
	private IntegerProperty ligne;
	private DoubleProperty longitude;
	private DoubleProperty latitude;
	private StringProperty nom;
	
	public int getLigne() { return ligne.get(); }
	public void setLigne(int ligne) { this.ligne.set(ligne); }
	public IntegerProperty ligneProperty() { return this.ligne; }
	
	public int getId() { return id.get(); }
	public void setId(int id) { this.id.set(id); }
	public IntegerProperty idProperty() { return this.id; }
	
	public double getLongitude() { return this.longitude.get(); }
    public void setLongitude(double longitude) { this.longitude.set(longitude); }
    public DoubleProperty longitudeProperty() {return this.longitude; }
    
    public double getLatitude() { return this.latitude.get(); }
    public void setLatitude(double latitude) { this.latitude.set(latitude); }
    public DoubleProperty latitudeProperty() {return this.latitude; }
    
    public String getNom() { return this.nom.get(); }
    public void setNom(String nom) { this.nom.set(nom); }
    public StringProperty nomProperty() {return this.nom; }
	
    public Station_Ligne(int id, int ligne, double longitude, double latitude, String nom) {
    	this.id = new SimpleIntegerProperty(id);
        this.ligne = new SimpleIntegerProperty(ligne);
        this.longitude = new SimpleDoubleProperty(longitude);
        this.latitude = new SimpleDoubleProperty(latitude);
        this.nom = new SimpleStringProperty(nom);
   }
}
