package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.stage.Stage;
import models.UserSession;

public class ControleurAccueil implements Initializable{
	
//	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML private ButtonBar btnBar;
	@FXML private Button btnBarAdmin;
	@FXML private Button btnBarUser;
	
	public void switchToLignesDeBusPage(ActionEvent e) throws IOException {
		URL location = getClass().getResource("LignesDeBus.fxml");
		root = FXMLLoader.load(location);
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToLignesDeTrainsPage(ActionEvent e) throws IOException {
		System.out.println("#switchToLignesDeTrainsPage");
		URL location = getClass().getResource("LignesDeTrain.fxml");
		root = FXMLLoader.load(location);
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		System.out.println(UserSession.getRole());
//		if (UserSession.getRole().equals("ordinaire")) {
//			btnBarAdmin.setManaged(false);
//		} else {
//			btnBarUser.setManaged(false);
//			throw new Error("Unauthorized access");
//		}
	}
}
