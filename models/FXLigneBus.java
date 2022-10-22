package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FXLigneBus {
	private IntegerProperty numero;
	private StringProperty parcours;
	
	public int getNumero() { return numero.get(); }
	public void setNumero(int numero) { this.numero.set(numero); }
	public IntegerProperty numeroProperty() { return this.numero; }
	
	public String getParcours() { return this.parcours.get(); }
    public void setParcours(String parcours) { this.parcours.set(parcours); }
    public StringProperty parcoursProperty() {return this.parcours; }
	
    public FXLigneBus(int numero, String parcours){
        this.numero = new SimpleIntegerProperty(numero);
        this.parcours = new SimpleStringProperty(parcours);
   }
	
}
