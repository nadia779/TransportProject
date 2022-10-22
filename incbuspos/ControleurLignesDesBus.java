package incbuspos;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.ControleurCommun;
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
import models.FXLigneBus;

public class ControleurLignesDesBus implements Initializable {
	@FXML private TableView<FXLigneBus> tableauDesLignes;
	@FXML private TableColumn<FXLigneBus, Integer> numero;
	@FXML private TableColumn<FXLigneBus, String> parcours;
	
	@FXML
	public void goBackToAccueil(ActionEvent e) throws IOException {
//		URL location = ControleurCommun.class.getResource("IncrementBusPositions.fxml");
//		Parent root = FXMLLoader.load(location);
//		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//		Scene scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
		System.out.println("same page");
	}
	
	public ObservableList<FXLigneBus> getLignes() {
		ObservableList<FXLigneBus> lignes = FXCollections.observableArrayList();
		lignes.add(new FXLigneBus(1, "Rabat - Temara"));
		lignes.add(new FXLigneBus(2, "Rabat - Salé"));
		lignes.add(new FXLigneBus(3, "Skhirate - Temara"));
		return lignes;
	}
	
	public void populateTable() {
		numero.setCellValueFactory(data -> data.getValue().numeroProperty().asObject());
		parcours.setCellValueFactory(data -> data.getValue().parcoursProperty());
		tableauDesLignes.setItems(getLignes());
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		populateTable();
		tableauDesLignes.setRowFactory( tv -> {
		    TableRow<FXLigneBus> row = new TableRow<>();
//		    row.setStyle("-fx-cursor: hand");
		    row.styleProperty().bind(Bindings.when(row.hoverProperty())
                    .then("-fx-font-weight: bold; -fx-cursor: hand")
                    .otherwise("-fx-font-weight: 400; -fx-cursor: hand"));
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() ) {
		        	FXLigneBus rowData = row.getItem();
		        	try {
//		        		goToIncBusPosInt(event , rowData.getNumero()); /* **** */
		        		goToListeDesBus(event , rowData.getNumero());
//		        		System.out.println("roww : " + rowData.getNumero());
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		    });
		    return row ;
		});
	}
	public void goToListeDesBus(Event e , int numLigne) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeDesBus.fxml"));

		ControleurListeDesBus contListeDesBus = new ControleurListeDesBus(numLigne);
		loader.setController(contListeDesBus);

		Parent root = loader.load();
		
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
//	public void goToIncBusPosInt(Event e , int numLigne) throws IOException {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("IncrementBusPositionsInt.fxml"));
//
//		ControleurIncBusPosInt contIncBusPosInt = new ControleurIncBusPosInt(numLigne);
//		loader.setController(contIncBusPosInt);
//
//		Parent root = loader.load();
//		
//		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//		Scene scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
//	}
}