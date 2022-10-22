package incbuspos;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.ControleurCommun;
import dao.BusDao;
import dao.Station_LigneDao;
import dao.impl.BusDaoImpl;
import dao.impl.Station_LigneDaoImpl;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Bus;
import models.Station_Ligne;

public class ControleurIncBusPosInt implements Initializable {
	@FXML private TableView<Station_Ligne> tableauDesStations;
	@FXML private TableColumn<Station_Ligne, Integer> ligne;
	@FXML private TableColumn<Station_Ligne, String> nom;
	private int numLigne;
	private int idBus;
	public ControleurIncBusPosInt(int numLigne, int idBus) {
		super();
		this.numLigne = numLigne;
		this.idBus = idBus;
	}
	
	public void goBackToListeDesBus(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeDesBus.fxml"));
		ControleurListeDesBus contListeDesBus = new ControleurListeDesBus(numLigne);
		loader.setController(contListeDesBus);
		Parent root = loader.load();

		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public ObservableList<Station_Ligne> getSationsLignes(int ligne) {
		ObservableList<Station_Ligne> stations_lignes = FXCollections.observableArrayList();
		Station_LigneDao sldi = new Station_LigneDaoImpl();
		stations_lignes.addAll(sldi.getStation_Lignes(ligne));
		return stations_lignes;
	}
	
	public void populateTable() {
		ligne.setCellValueFactory(data -> data.getValue().ligneProperty().asObject());
		nom.setCellValueFactory(data -> data.getValue().nomProperty());
		tableauDesStations.setItems(getSationsLignes(numLigne));
	}
	
	public boolean updatePosition(Event e, int id, double latitude, double longitude) {
		BusDao bdi = new BusDaoImpl();
		return bdi.updatePosition(id, latitude, longitude);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		System.out.println("init 1st line : " + numLigne);
		populateTable();
		tableauDesStations.setRowFactory( tv -> {
		    TableRow<Station_Ligne> row = new TableRow<>();
//		    row.setStyle("-fx-cursor: hand");
		    row.styleProperty().bind(Bindings.when(row.hoverProperty())
                    .then("-fx-font-weight: bold; -fx-cursor: hand")
                    .otherwise("-fx-font-weight: 400; -fx-cursor: hand"));
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() ) {
		        	Station_Ligne rowData = row.getItem();
		        	updatePosition(event, idBus, rowData.getLatitude(), rowData.getLongitude());
		        }
		    });
		    return row ;
		});
//		System.out.println("init last line : " + numLigne);
	}
}