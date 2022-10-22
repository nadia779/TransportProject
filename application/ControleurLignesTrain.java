package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.FXLigneBus;
import models.FXLigneTrain;
import models.UserSession;
import projets1.ViewMap5;

public class ControleurLignesTrain implements Initializable {
	@FXML private TableView<FXLigneTrain> tableauDesLignes;
	@FXML private TableColumn<FXLigneTrain, Integer> nombre;
	@FXML private TableColumn<FXLigneTrain, String> parcours;
	@FXML private TableColumn<FXLigneTrain, String> nom;
	@FXML private TextField filterField;
	@FXML private ComboBox<String> filtreNomDDM;
	@FXML private ComboBox<String> filtreTrajetDDM;
	@FXML private ButtonBar btnBar;
	@FXML private Button btnBarAdmin;
	@FXML private Button btnBarUser;
	
	public void goBackToAccueil(ActionEvent e) throws IOException {
		ControleurCommun.goBackToAccueil(e);
	}
	public void goBackToAccueilAdmin(ActionEvent e) throws IOException {
		ControleurCommun.goBackToAccueilAdmin(e);
	}
	
	public ObservableList<FXLigneTrain> getLignes() {
		ObservableList<FXLigneTrain> lignes = FXCollections.observableArrayList();
		lignes.add(new FXLigneTrain("Atlas East", "Er-Rich - Marrakech", 20));
		lignes.add(new FXLigneTrain("Al Boraq", "Casa - Tanger", 10));
		return lignes;
	}
	
	public void initFiltreNomDDM() {
		// I - Populating
		for (FXLigneTrain train : getLignes()) {
			if (filtreNomDDM.getItems().contains(train.getNom())) continue;
			filtreNomDDM.getItems().add(train.getNom());
		}
	}
	
	public void filtreNomDDMOnAction() {
		// I - Filtering Functionality
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<FXLigneTrain> filteredData = new FilteredList<>(getLignes(), p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filteredData.setPredicate(train -> {
			// If filter text is empty, display all trains.
			if (filtreNomDDM.getValue() == null || filtreNomDDM.getValue().isEmpty()) {
				return true;
			}
			// Compare first name and last name of every person with filter text.
			String lowerCaseFilter = filtreNomDDM.getValue().toLowerCase();

			if (train.getNom().toLowerCase().contains(lowerCaseFilter)) return true;
			return false; // Does not match.
		});
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<FXLigneTrain> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tableauDesLignes.comparatorProperty());
		
		// III - Populating by : Adding sorted (and filtered) data to the table.
		tableauDesLignes.setItems(sortedData);
	}
	
	public void clearFiltreNomDDM() {
		filtreNomDDM.valueProperty().set(null);
	}
	
	public void initFiltreTrajetDDM() {
		// I - Populating
		// new ol
		ObservableList<String> filtreTrajetDDMValues = FXCollections.observableArrayList();
		// populating the ol if value not already there
		for (FXLigneTrain train : getLignes()) {
			if (filtreTrajetDDMValues.contains(train.getParcours())) continue;
			filtreTrajetDDMValues.add(train.getParcours());
			System.out.println(filtreTrajetDDMValues);
		}
		// sorting the ol then adding it to the filtre comboBox
		Collections.sort(filtreTrajetDDMValues);
		filtreTrajetDDM.getItems().addAll(filtreTrajetDDMValues);
	}
	
	public void filtreTrajetDDMOnAction() {
		// I - Filtering Functionality
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<FXLigneTrain> filteredData = new FilteredList<>(getLignes(), p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filteredData.setPredicate(train -> {
			// If filter text is empty, display all trains.
			if (filtreTrajetDDM.getValue() == null || filtreTrajetDDM.getValue().isEmpty()) {
				return true;
			}
			// Compare first name and last name of every person with filter text.
			String lowerCaseFilter = filtreTrajetDDM.getValue().toLowerCase();
			
			if (train.getParcours().toLowerCase().contains(lowerCaseFilter)) return true; // match
			return false; // Does not match.
		});
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<FXLigneTrain> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tableauDesLignes.comparatorProperty());
		
		// III - Populating by : Adding sorted (and filtered) data to the table.
		tableauDesLignes.setItems(sortedData);
	}
	
	public void clearFiltreTrajetDDM() {
		filtreTrajetDDM.valueProperty().set(null);
	}
	
	public void initTableauDesLignes() {
		// I - cellValueFactory
		nombre.setCellValueFactory(data -> data.getValue().nombreProperty().asObject());
		parcours.setCellValueFactory(data -> data.getValue().parcoursProperty());
		nom.setCellValueFactory(data -> data.getValue().nomProperty());
		// II - Filtering Functionality
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<FXLigneTrain> filteredData = new FilteredList<>(getLignes(), p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(train -> {
				// If filter text is empty, display all trains.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (train.getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (train.getParcours().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<FXLigneTrain> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tableauDesLignes.comparatorProperty());
		
		// III - Populating by : Adding sorted (and filtered) data to the table.
		tableauDesLignes.setItems(sortedData);
	}
	
	public void logout(ActionEvent e) throws IOException {
		UserSession.cleanUserSession();
		ControleurCommun.goBackToMain(e);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableauDesLignes();
		initFiltreNomDDM();
		initFiltreTrajetDDM();
		tableauDesLignes.setRowFactory( tv -> {
		    TableRow<FXLigneTrain> row = new TableRow<>();
//		    row.setStyle("-fx-cursor: hand");
		    row.styleProperty().bind(Bindings.when(row.hoverProperty())
                    .then("-fx-font-weight: bold; -fx-cursor: hand")
                    .otherwise("-fx-font-weight: 400; -fx-cursor: hand"));
		    row.setOnMouseClicked(event -> {
		        if (! row.isEmpty() ) {
		        	FXLigneTrain rowData = row.getItem();
		        	ControleurCommun cc = new ControleurCommun();
//		        	ControleurMap cc = new ControleurMap();
		        	try {
		        		ViewMap5.acCounter = 0;
						cc.goToMap(event, 0 , "train", rowData.getNom());
					} catch (IOException | SQLException e) {
						e.printStackTrace();
					}
//		            System.out.println(rowData.getNumero());
		        }
		    });
		    return row ;
		});
//		if (UserSession.getRole().equals("ordinaire")) {
//			btnBarAdmin.setManaged(false);
//		}
	}
}
