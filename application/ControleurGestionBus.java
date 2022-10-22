package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import dao.BusDao;
import dao.impl.BusDaoImpl;
//import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Utilisateurs;
import models.Bus;
import models.FXLigneTrain;
import models.UserSession;
import db.Connect;

public class ControleurGestionBus implements Initializable {
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
	@FXML private TextField filterField;
	@FXML private TextField immaTextField;
	@FXML private TextField nbrPMaxTextField;
	@FXML private TextField ligneTextField;
	@FXML private TextField villeTextField;
	@FXML private TextField IdTextField;
	@FXML private Button InsertButton;
	@FXML private Button UpdateButton;
	@FXML private Button DeleteButton;
	@FXML private ButtonBar btnBar;
	@FXML private Button btnBarAdmin;
	@FXML private Button btnBarUser;
	
	@FXML 
	private void handleButtonOnAction(ActionEvent event) {
		if(event.getSource()==InsertButton) {
			insertRecord(); 
		}else if(event.getSource()==UpdateButton) {
			UpdateRecord() ; 
		}else {
			 DeleteRecord() ;
		}
	}
	@FXML
	private void handleTableauSelection(MouseEvent event) {
		Bus bus = tableauDesBus.getSelectionModel().getSelectedItem();
		IdTextField.setText(""+bus.getId());
		immaTextField.setText(bus.getCodeImmatriculation());
		nbrPMaxTextField.setText(""+bus.getNbrPlacesMax());
		ligneTextField.setText(""+bus.getLigne());
		villeTextField.setText(bus.getVille());
	 }
	
	private void insertRecord() {
		Connection cnx= db.Connect.getConnection();
        String insert = "INSERT INTO vehicules (codeImmatriculation,nbrPlacesMax,ligneBus,villeBus,type) VALUES(?,?,?,?,'bus')";
        try {
           PreparedStatement pres = cnx.prepareStatement(insert);
           //pres.setString(1, IdTextField.getText());
           pres.setString(1, immaTextField.getText());
           pres.setString(2, nbrPMaxTextField.getText());
           pres.setString(3, ligneTextField.getText());
           pres.setString(4, villeTextField.getText());
           pres.executeUpdate();
           initTableauDesBus();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	 private void UpdateRecord() {
			Connection cnx=Connect.getConnection();
			String update = "UPDATE vehicules SET  codeImmatriculation=? , nbrPlacesMax=? , ligneBus=? ,villeBus=? where id=?";
			try {
				PreparedStatement pres = cnx.prepareStatement(update);
				pres.setString(1, immaTextField.getText());
		           pres.setString(2, nbrPMaxTextField.getText());
		           pres.setString(3, ligneTextField.getText());
		           pres.setString(4, villeTextField.getText());
		           pres.setInt(5, Integer.parseInt(IdTextField.getText()));
		           pres.executeUpdate();
		           initTableauDesBus();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
	 }
	 private void DeleteRecord() {
			Connection cnx=Connect.getConnection();
			String delete= "DELETE FROM vehicules where id=?";
			 try {
		           PreparedStatement st = cnx.prepareStatement(delete);
		            st.setInt(1, Integer.parseInt(IdTextField.getText()));
		            st.executeUpdate();
		            initTableauDesBus();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
	 }
//	@FXML private ComboBox<String> filtreCodeImmDDM;
//	@FXML private ComboBox<Integer> filtreLigneDDM;
//	@FXML private ComboBox<String> filtreVilleDDM;
	
	public void goBackToAccueil(ActionEvent e) throws IOException {
		ControleurCommun.goBackToAccueil(e);
	}
	public void goBackToAccueilAdmin(ActionEvent e) throws IOException {
		ControleurCommun.goBackToAccueilAdmin(e);
	}
	public void logout(ActionEvent e) throws IOException {
		UserSession.cleanUserSession();
		ControleurCommun.goBackToMain(e);
	}

	public ObservableList<Bus> getBuses() {
		ObservableList<Bus> buses = FXCollections.observableArrayList();
		BusDao bdi = new BusDaoImpl();
		buses.addAll(bdi.getBuses());
//		System.out.println(lignes);
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
	public void initTableauDesBus() {
		initCVF();
		// II - Filtering Functionality
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Bus> filteredData = new FilteredList<>(getBuses(), p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(bus -> {
				// If filter text is empty, display all trains.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (Integer.toString(bus.getLigne()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (bus.getVille().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				} else if (bus.getCodeImmatriculation().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Bus> sortedData = new SortedList<>(filteredData);
		System.out.println(tableauDesBus);
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tableauDesBus.comparatorProperty());
		
		// III - Populating by : Adding sorted (and filtered) data to the table.
		tableauDesBus.setItems(sortedData);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableauDesBus();
//		System.out.println(UserSession.getRole());
//		if (UserSession.getRole().equals("administrateur")) {
//			btnBarUser.setManaged(false);
//		}
	}
}
