package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bus extends Vehicule {
	private IntegerProperty ligne ;
    private StringProperty ville;
    
	public Bus(int id, String codeImmatriculation, boolean fumee, float co2quantite, int vitesse, float temperature, int nbrPlacesMax,
			int nbrPlacesVacantes, int nbrPersonnesAbord, int ligne, String ville) {
		super(id, codeImmatriculation, fumee, co2quantite, vitesse, temperature, nbrPlacesMax, nbrPlacesVacantes, nbrPersonnesAbord);
		this.ligne = new SimpleIntegerProperty(ligne);
		this.ville = new SimpleStringProperty(ville);
	}
	public StringProperty villeProperty() { return this.ville; }
	public IntegerProperty ligneProperty() { return this.ligne; }

	public int getLigne() {
		return ligne.get();
	}

	public void setLigne(int ligne) {
		this.ligne.set(ligne);
	}

	public String getVille() {
		return ville.get();
	}

	public void setVille(String ville) {
		this.ville.set(ville);
	}

	@Override
	public String toString() {
		return super.toString() + " ligne=" + ligne.get() + ", ville=" + ville.get() + "]";
	}
	
}
