package models;


import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;

public abstract class Vehicule {
//	private final String codeImmatriculation; 
//	private boolean fumee ; 
//	private float co2quantite ; 
//	private int vitesse ; 
//	private float temperature ;
//	private final int nbrPlacesMax ; 
//	private int nbrPlacesVacantes ; 
//	private int nbrPersonnesAbord ;
	private IntegerProperty id ;
	private final StringProperty codeImmatriculation; 
	private BooleanProperty fumee ; 
	private FloatProperty co2quantite ; 
	private FloatProperty vitesse ; 
	private FloatProperty temperature ;
	private final IntegerProperty nbrPlacesMax ; 
	private IntegerProperty nbrPlacesVacantes ; 
	private IntegerProperty nbrPersonnesAbord ;
	
	public IntegerProperty idProperty() { return this.id; }
	public StringProperty codeImmatriculationProperty() { return this.codeImmatriculation; }
	public FloatProperty co2quantiteProperty() { return this.co2quantite; }
	public FloatProperty vitesseProperty() { return this.vitesse; }
	public FloatProperty temperatureProperty() { return this.temperature; }
	public IntegerProperty nbrPlacesMaxProperty() { return this.nbrPlacesMax; }
	public IntegerProperty nbrPlacesVacantesProperty() { return this.nbrPlacesVacantes; }
	public IntegerProperty nbrPersonnesAbordProperty() { return this.nbrPersonnesAbord; }
	public BooleanProperty fumeeProperty() { return this.fumee; }

	public Vehicule(String codeImmatriculation, boolean fumee, float co2quantite, int vitesse, float temperature, int nbrPlacesMax,
			int nbrPlacesVacantes, int nbrPersonnesAbord) {
		super();
		this.codeImmatriculation = new SimpleStringProperty(codeImmatriculation);
		this.fumee = new SimpleBooleanProperty(fumee);
		this.co2quantite = new SimpleFloatProperty(co2quantite);
		this.vitesse = new SimpleFloatProperty(vitesse);
		this.nbrPlacesMax = new SimpleIntegerProperty(nbrPlacesMax);
		this.nbrPlacesVacantes = new SimpleIntegerProperty(nbrPlacesVacantes);
		this.nbrPersonnesAbord = new SimpleIntegerProperty(nbrPersonnesAbord);
		this.temperature = new SimpleFloatProperty(temperature);
	}
	public Vehicule(int id, String codeImmatriculation, boolean fumee, float co2quantite, int vitesse, float temperature, int nbrPlacesMax,
			int nbrPlacesVacantes, int nbrPersonnesAbord) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.codeImmatriculation = new SimpleStringProperty(codeImmatriculation);
		this.fumee = new SimpleBooleanProperty(fumee);
		this.co2quantite = new SimpleFloatProperty(co2quantite);
		this.vitesse = new SimpleFloatProperty(vitesse);
		this.nbrPlacesMax = new SimpleIntegerProperty(nbrPlacesMax);
		this.nbrPlacesVacantes = new SimpleIntegerProperty(nbrPlacesVacantes);
		this.nbrPersonnesAbord = new SimpleIntegerProperty(nbrPersonnesAbord);
		this.temperature = new SimpleFloatProperty(temperature);
	}

	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);
	}
	public float getTemperature() {
		return temperature.get();
	}

	public void setTemperature(float temperature) {
		this.temperature.set(temperature);
	}

	public int getNbrPlacesMax() {
		return nbrPlacesMax.get();
	}

	public String getCodeImmatriculation() {
		return codeImmatriculation.get();
	}
	
	public float getVitesse() {
		return vitesse.get();
	}
	public void setVitesse(float vitesse) {
		this.vitesse.set(vitesse);
	}

	public int getNbrPlacesVacantes() {
		return nbrPlacesVacantes.get();
	}
	public void setNbrPlacesVacantes(int nbrPlacesVacantes) {
		this.nbrPlacesVacantes.set(nbrPlacesVacantes);
	}
	public float getCo2quantite() {
		return co2quantite.get();
	}
	public void setCo2quantite(float co2quantite) {
		this.co2quantite.set(co2quantite);
	}
	public boolean isFumee() {
		return fumee.get();
	}
	public void setFumee(boolean fumee) {
		this.fumee.set(fumee);
	}
	public int getNbrPersonnesAbord() {
		return nbrPersonnesAbord.get();
	}
	public void setNbrPersonnesAbord(int nbrPersonnesAbord) {
		this.nbrPersonnesAbord.set(nbrPersonnesAbord);
	}

	@Override
	public String toString() {
		return "Vehicule [codeImmatriculation=" + codeImmatriculation.get() + ", fumee=" + fumee.get() + ", co2quantite="
				+ co2quantite.get() + ", vitesse=" + vitesse.get() + ", nbrPlacesMax=" + nbrPlacesMax.get() + ", nbrPlacesVacantes="
				+ nbrPlacesVacantes.get() + ", nbrPersonnesAbord=" + nbrPersonnesAbord.get() + ", temperature=" + temperature.get() + "]";
	} 
	
}
