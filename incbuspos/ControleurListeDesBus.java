package incbuspos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.ControleurCommun;
import dao.BusDao;
import dao.impl.BusDaoImpl;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Bus;
import models.FXLigneBus;
import models.FXLigneTrain;

public class ControleurListeDesBus implements Initializable {
	@FXML private TableView<Bus> tableauDesBus;
	@FXML private TableColumn<Bus, Integer> id;
	@FXML private TableColumn<Bus, String> codeImmatriculation;
	@FXML private TableColumn<Bus, Boolean> fumee;
	@FXML private TableColumn<Bus, Float> co2quantite;
	@FXML private TableColumn<Bus, Float> vitesse;
	@FXML private TableColumn<Bus, Float> temperature;
	@FXML private TableColumn<Bus, Integer> nbrPlacesMax;
	@FXML private TableColumn<Bus, Integer> nbrPlacesVacantes;
	@FXML private TableColumn<Bus, Integer> nbrPersonnesAbord;
	@FXML private TableColumn<Bus, Integer> ligne;
	@FXML private TableColumn<Bus, String> ville;
	
	private int numLigne;
	public ControleurListeDesBus(int numLigne) {
		super();
		this.numLigne = numLigne;
	}
	
	public void goBackToAccueil(ActionEvent e) throws IOException {
		URL location = getClass().getResource("IncrementBusPositions.fxml");
		Parent root = FXMLLoader.load(location);
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public ObservableList<Bus> getBuses(int ligne) {
		ObservableList<Bus> buses = FXCollections.observableArrayList();
		BusDao bdi = new BusDaoImpl();
		buses.addAll(bdi.getBuses(ligne));
		return buses;
	}
	public void initCVF() {	
		id.setCellValueFactory(data -> data.getValue().idProperty().asObject());
		codeImmatriculation.setCellValueFactory(data -> data.getValue().codeImmatriculationProperty());
		fumee.setCellValueFactory(data -> data.getValue().fumeeProperty().asObject());
		vitesse.setCellValueFactory(data -> data.getValue().vitesseProperty().asObject());
		co2quantite.setCellValueFactory(data -> data.getValue().co2quantiteProperty().asObject());		
		temperature.setCellValueFactory(data -> data.getValue().temperatureProperty().asObject());
		nbrPlacesMax.setCellValueFactory(data -> data.getValue().nbrPlacesMaxProperty().asObject());
		nbrPlacesVacantes.setCellValueFactory(data -> data.getValue().nbrPlacesVacantesProperty().asObject());
		nbrPersonnesAbord.setCellValueFactory(data -> data.getValue().nbrPersonnesAbordProperty().asObject());
		ligne.setCellValueFactory(data -> data.getValue().ligneProperty().asObject());
		ville.setCellValueFactory(data -> data.getValue().villeProperty());
	}
	public void initTableauDesBus(int ligne) {
		initCVF();
		// II - Filtering Functionality
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Bus> filteredData = new FilteredList<>(getBuses(ligne), p -> true);
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Bus> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tableauDesBus.comparatorProperty());
		
		// III - Populating by : Adding sorted (and filtered) data to the table.
		tableauDesBus.setItems(sortedData);
		tableauDesBus.setRowFactory( tv -> {
		    TableRow<Bus> row = new TableRow<>();
//		    row.setStyle("-fx-cursor: hand");
		    row.styleProperty().bind(Bindings.when(row.hoverProperty())
                    .then("-fx-font-weight: bold; -fx-cursor: hand")
                    .otherwise("-fx-font-weight: 400; -fx-cursor: hand"));
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() ) {
		        	Bus rowData = row.getItem();
		        	try {
		        		goToIncBusPosInt(event , rowData.getLigne(), rowData.getId()); /* **** */
//		        		System.out.println("roww : " + rowData.getNumero());
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		    });
		    return row ;
		});
	}
	
	public void goToIncBusPosInt(Event e , int numLigne, int idBus) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("IncrementBusPositionsInt.fxml"));

		ControleurIncBusPosInt contIncBusPosInt = new ControleurIncBusPosInt(numLigne,idBus);
		loader.setController(contIncBusPosInt);

		Parent root = loader.load();
		
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableauDesBus(numLigne);
	}
}
