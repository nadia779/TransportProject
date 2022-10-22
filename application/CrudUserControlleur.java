package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.UserSession;
import models.Utilisateurs ;
import db.Connect;


public class CrudUserControlleur implements Initializable  {
	@FXML private TableView<Utilisateurs> UsersTable;
	@FXML private TableColumn<Utilisateurs, Integer> IdCol;
	@FXML private TableColumn<Utilisateurs, String> PrenomCol;
	@FXML private TableColumn<Utilisateurs, String> NomCol;
	@FXML private TableColumn<Utilisateurs, String> EmailCol;
	@FXML private TableColumn<Utilisateurs, String> PasswordCol;
	@FXML private TableColumn<Utilisateurs, String> TeleCol;
	@FXML private TableColumn<Utilisateurs, String> VilleCol;
	@FXML private TableColumn<Utilisateurs, String> RoleCol;
	@FXML private TableColumn<Utilisateurs, Timestamp> DateCol;
	@FXML private TextField IdTextField;
	@FXML private TextField PrenomTextField;
	@FXML private TextField NomTextField;
	@FXML private TextField EmailTextField;
	@FXML private TextField PasswordTextField;
	@FXML private TextField TeleTextField;
	@FXML private TextField VilleTextField;
	@FXML private TextField RoleTextField;
	@FXML private TextField DateTextField;
	@FXML private Button InsertButton;
	@FXML private Button UpdateButton;
	@FXML private Button DeleteButton;
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
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		showUsers(); 
		
	}
	public ObservableList<Utilisateurs> getUtilisateurs() {
		ObservableList<Utilisateurs> users = FXCollections.observableArrayList();
		Connection cnx=Connect.getConnection();
		
		try {
			Statement statement = cnx.createStatement();
			String sql = "select * from utilisateurs";
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				Utilisateurs user = new Utilisateurs(result.getInt("id"), result.getString("prenom"),result.getString("nom"),result.getString("email"),
						result.getString("telephone"), result.getString("ville"), result.getString("password"),
					 result.getTimestamp("date_inscription") , result.getString("role")); 
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	 public void showUsers() {
		 ObservableList<Utilisateurs> list=getUtilisateurs() ;
		 IdCol.setCellValueFactory(new PropertyValueFactory<Utilisateurs , Integer>("id"));
		 PrenomCol.setCellValueFactory(new PropertyValueFactory<Utilisateurs , String>("prenom"));
		 NomCol.setCellValueFactory(new PropertyValueFactory<Utilisateurs , String>("nom"));
		 EmailCol.setCellValueFactory(new PropertyValueFactory<Utilisateurs , String>("email"));
		 PasswordCol.setCellValueFactory(new PropertyValueFactory<Utilisateurs , String>("password"));
		 TeleCol.setCellValueFactory(new PropertyValueFactory<Utilisateurs , String>("telephone"));
		 VilleCol.setCellValueFactory(new PropertyValueFactory<Utilisateurs , String>("ville"));
		 RoleCol.setCellValueFactory(new PropertyValueFactory<Utilisateurs,String >("role"));
		 DateCol.setCellValueFactory(new PropertyValueFactory<Utilisateurs , Timestamp>("date_inscription"));
		 UsersTable.setItems(list);
	    }
	 
	 private void insertRecord() {
			Connection cnx=Connect.getConnection();
	        String insert = "INSERT INTO utilisateurs (prenom,nom,email,telephone,ville,password,role,date_inscription)VALUES(?,?,?,?,?,?,?,?)";
	        try {
	           PreparedStatement pres = cnx.prepareStatement(insert);
	           //pres.setString(1, IdTextField.getText());
	           pres.setString(1, PrenomTextField.getText());
	           pres.setString(2, NomTextField.getText());
	           pres.setString(3, EmailTextField.getText());
	           pres.setString(4, TeleTextField.getText());
	           pres.setString(5, VilleTextField.getText());
	           pres.setString(6, PasswordTextField.getText());
	           pres.setString(7, RoleTextField.getText());
	           pres.setString(8, DateTextField.getText());
	           pres.executeUpdate();
	           showUsers();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	 private void UpdateRecord() {
			Connection cnx=Connect.getConnection();
			String update = "UPDATE utilisateurs SET  nom=? , prenom=? , email=? ,telephone=? , ville=? , password=? , role=? , date_inscription=? where id=?";
			try {
				PreparedStatement pres = cnx.prepareStatement(update);
			      pres.setString(1, NomTextField.getText());
		           pres.setString(2, PrenomTextField.getText());
		           pres.setString(3, EmailTextField.getText());
		           pres.setString(4, TeleTextField.getText());
		           pres.setString(5, VilleTextField.getText());
		           pres.setString(6, PasswordTextField.getText());
		           pres.setString(7, RoleTextField.getText());
		           pres.setString(8, DateTextField.getText());
		           pres.setInt(9, Integer.parseInt(IdTextField.getText()));
		           pres.executeUpdate();
		           showUsers();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
	 }
	 private void DeleteRecord() {
			Connection cnx=Connect.getConnection();
			String delete= "DELETE FROM utilisateurs where id=?";
			 try {
		           PreparedStatement st = cnx.prepareStatement(delete);
		            st.setInt(1, Integer.parseInt(IdTextField.getText()));
		            st.executeUpdate();
		            showUsers();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
	 }
	 @FXML
	 private void hadlemouseAction(MouseEvent event) {
		Utilisateurs user= UsersTable.getSelectionModel().getSelectedItem();
		IdTextField.setText("" +user.getId());
		PrenomTextField.setText(user.getPrenom());
		NomTextField.setText(user.getNom());
		EmailTextField.setText(user.getEmail());
		TeleTextField.setText(user.getTelephone());
	VilleTextField.setText(user.getVille());
	PasswordTextField.setText(user.getPassword());
	DateTextField.setText("" +user.getDate_inscription());
	 }
	 
	 public void goBackToAccueilAdmin(ActionEvent e) throws IOException {
			ControleurCommun.goBackToAccueilAdmin(e);
		}
	 
	 public void logout(ActionEvent e) throws IOException {
			UserSession.cleanUserSession();
			ControleurCommun.goBackToMain(e);
		}

		
	
}
