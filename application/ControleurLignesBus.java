package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import models.FXLigneBus;
import models.UserSession;
import projets1.ViewMap5;

public class ControleurLignesBus implements Initializable {
	@FXML private TableView<FXLigneBus> tableauDesLignes;
	@FXML private TableColumn<FXLigneBus, Integer> numero;
	@FXML private TableColumn<FXLigneBus, String> parcours;
	@FXML private ButtonBar btnBar;
	@FXML private Button btnBarAdmin;
	@FXML private Button btnBarUser;
	
	public void goBackToAccueil(ActionEvent e) throws IOException {
		ControleurCommun.goBackToAccueil(e);
	}
	public void goBackToAccueilAdmin(ActionEvent e) throws IOException {
		ControleurCommun.goBackToAccueilAdmin(e);
	}
	public ObservableList<FXLigneBus> getLignes() {
		ObservableList<FXLigneBus> lignes = FXCollections.observableArrayList();
		lignes.add(new FXLigneBus(1, "El Joulane - Hay Karima"));
		lignes.add(new FXLigneBus(2, "El Joulane - Salé Jadida"));
		lignes.add(new FXLigneBus(3, "Place de la Russie - Mahaj Riyad"));
		return lignes;
	}
	
	public void populateTable() {
		numero.setCellValueFactory(data -> data.getValue().numeroProperty().asObject());
		parcours.setCellValueFactory(data -> data.getValue().parcoursProperty());
		tableauDesLignes.setItems(getLignes());
	}
	public void logout(ActionEvent e) throws IOException {
		UserSession.cleanUserSession();
		ControleurCommun.goBackToMain(e);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		if (UserSession.getRole().equals("ordinaire")) {
//			btnBarAdmin.setManaged(false);
//		}
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
		        	ControleurCommun cc = new ControleurCommun();
//		        	ControleurMap cc = new ControleurMap();
		        	try {
		        		ViewMap5.acCounter = 0;
						cc.goToMap(event, rowData.getNumero(), "bus", null);
					} catch (IOException | SQLException e) {
						e.printStackTrace();
					}
//		            System.out.println(rowData.getNumero());
		        }
		    });
		    return row ;
		});
	}
}