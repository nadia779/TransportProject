package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dao.TrainDao;
import dao.impl.TrainDaoImpl;
import db.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Train;
import models.UserSession;
import models.Train;

public class ControleurGestionTrain implements Initializable {
	@FXML private TableView<Train> tableauDesTrains;
	@FXML private TableColumn<Train, Integer> id;
	@FXML private TableColumn<Train, String> codeImmatriculation;
	@FXML private TableColumn<Train, Boolean> fumee;
	@FXML private TableColumn<Train, Float> co2quantite;
	@FXML private TableColumn<Train, Float> vitesse;
	@FXML private TableColumn<Train, Float> temperature;
	@FXML private TableColumn<Train, Integer> nbrPlacesMax;
	@FXML private TableColumn<Train, Integer> nbrPlacesVacantes;
	@FXML private TableColumn<Train, Integer> nbrPersonnesAbord;
	@FXML private TableColumn<Train, Integer> nbrComp;
	@FXML private TableColumn<Train, String> nom;
	@FXML private TextField filterField;
	@FXML private TextField immaTextField;
	@FXML private TextField nbrPMaxTextField;
	@FXML private TextField nomTextField;
	@FXML private TextField nbrCompTextField;
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
		Train train = tableauDesTrains.getSelectionModel().getSelectedItem();
		IdTextField.setText(""+train.getId());
		immaTextField.setText(train.getCodeImmatriculation());
		nbrPMaxTextField.setText(""+train.getNbrPlacesMax());
		nomTextField.setText(train.getNom());
		nbrCompTextField.setText(""+train.getNbrComp());
	 }
	
	private void insertRecord() {
		Connection cnx= db.Connect.getConnection();
        String insert = "INSERT INTO vehicules (codeImmatriculation,nbrPlacesMax,nomTrain,nbrCompTrain,type) VALUES(?,?,?,?,'train')";
        try {
           PreparedStatement pres = cnx.prepareStatement(insert);
           //pres.setString(1, IdTextField.getText());
           pres.setString(1, immaTextField.getText());
           pres.setString(2, nbrPMaxTextField.getText());
           pres.setString(3, nomTextField.getText());
           pres.setString(4, nbrCompTextField.getText());
           pres.executeUpdate();
           initTableauDesTrains();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	 private void UpdateRecord() {
			Connection cnx=Connect.getConnection();
			String update = "UPDATE vehicules SET  codeImmatriculation=? , nbrPlacesMax=? , nomTrain=? ,nbrCompTrain=? where id=?";
			try {
				PreparedStatement pres = cnx.prepareStatement(update);
				pres.setString(1, immaTextField.getText());
		           pres.setString(2, nbrPMaxTextField.getText());
		           pres.setString(3, nomTextField.getText());
		           pres.setString(4, nbrCompTextField.getText());
		           pres.setInt(5, Integer.parseInt(IdTextField.getText()));
		           pres.executeUpdate();
		           initTableauDesTrains();
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
		            initTableauDesTrains();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
	 }
	public void goBackToAccueil(ActionEvent e) throws IOException {
		ControleurCommun.goBackToAccueil(e);
	}
	public void goBackToAccueilAdmin(ActionEvent e) throws IOException {
		ControleurCommun.goBackToAccueilAdmin(e);
	}
	
	public ObservableList<Train> getTrains() {
		ObservableList<Train> trains = FXCollections.observableArrayList();
		TrainDao bdi = new TrainDaoImpl();
		trains.addAll(bdi.getTrains());
//		System.out.println(nbrComps);
		return trains;
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
		nbrComp.setCellValueFactory(data -> data.getValue().nbrCompProperty().asObject());
		nom.setCellValueFactory(data -> data.getValue().nomProperty());
	}
	public void initTableauDesTrains() {
		initCVF();
		// II - Filtering Functionality
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Train> filteredData = new FilteredList<>(getTrains(), p -> true);
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(train -> {
				// If filter text is empty, display all trains.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
//				if (Integer.toString(train.getNbrComp()).toLowerCase().contains(lowerCaseFilter)) {
//					return true; // Filter matches first name.
//				} else
				if (train.getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				} else if (train.getCodeImmatriculation().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Train> sortedData = new SortedList<>(filteredData);
		// 4. Bind the SortedList comparator to the TableView comparator.
		System.out.println(tableauDesTrains);
		sortedData.comparatorProperty().bind(tableauDesTrains.comparatorProperty());
		// III - Populating by : Adding sorted (and filtered) data to the table.
		tableauDesTrains.setItems(sortedData);
	}
	public void logout(ActionEvent e) throws IOException {
		UserSession.cleanUserSession();
		ControleurCommun.goBackToMain(e);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTableauDesTrains();
//		System.out.println(UserSession.getRole());
//		if (UserSession.getRole().equals("administrateur")) {
//			btnBarUser.setManaged(false);
//		}
	}
}
