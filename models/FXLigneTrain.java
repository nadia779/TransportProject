package models;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FXLigneTrain {
	private IntegerProperty nombre;
	private StringProperty nom;
	private StringProperty parcours;
	
	@Override
	public String toString() {
		return "FXLigneTrain [nombre=" + nombre.get() + ", nom=" + nom.get() +
				", parcours=" + parcours.get() + "]";
	}
	
	public int getNombre() { return nombre.get(); }
	public void setNombre(int nombre) { this.nombre.set(nombre); }
	public IntegerProperty nombreProperty() { return this.nombre; }
	
	public String getParcours() { return this.parcours.get(); }
    public void setParcours(String parcours) { this.parcours.set(parcours); }
    public StringProperty parcoursProperty() {return this.parcours; }
	
    public String getNom() { return this.nom.get(); }
    public void setNom(String nom) { this.nom.set(nom); }
    public StringProperty nomProperty() {return this.nom; }
    
    public FXLigneTrain(String nom, String parcours, int nombre){
        this.nombre = new SimpleIntegerProperty(nombre);
        this.parcours = new SimpleStringProperty(parcours);
        this.nom = new SimpleStringProperty(nom);
   }
}
