package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Train extends Vehicule {
	private IntegerProperty nbrComp ;
    private StringProperty nom;
    
	public Train(int id, String codeImmatriculation, boolean fumee, float co2quantite, int vitesse, float temperature, int nbrPlacesMax,
			int nbrPlacesVacantes, int nbrPersonnesAbord, int nbrComp, String nom) {
		super(id, codeImmatriculation, fumee, co2quantite, vitesse, temperature, nbrPlacesMax, nbrPlacesVacantes, nbrPersonnesAbord);
		this.nbrComp = new SimpleIntegerProperty(nbrComp);
		this.nom = new SimpleStringProperty(nom);
	}
	
	public StringProperty nomProperty() { return this.nom; }
	public IntegerProperty nbrCompProperty() { return this.nbrComp; }

	public int getNbrComp() {
		return nbrComp.get();
	}

	public void setNbrComp(int nbrComp) {
		this.nbrComp.set(nbrComp);
	}

	public String getNom() {
		return nom.get();
	}

	public void setNom(String nom) {
		this.nom.set(nom);
	}

	@Override
	public String toString() {
		return super.toString() + " nbrComp=" + nbrComp.get() + ", nom=" + nom.get() + "]";
	}
		
}