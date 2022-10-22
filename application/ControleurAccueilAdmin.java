package application;

import java.io.IOException;
import java.net.URL;
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

public class ControleurAccueilAdmin implements Initializable {
//	@FXML
	private Stage stage;
	private Stage stage2;
	private Stage stage3;
	private Scene scene;
	private Scene scene2;
	private Scene scene3;
	private Parent root;
	private Parent root2;
	private Parent root3;
	@FXML private ButtonBar btnBar;
	@FXML private Button btnBarAdmin;
	@FXML private Button btnBarUser;
	
	public void switchToGestionBusPage(ActionEvent e) throws IOException {
		URL location = getClass().getResource("GestionBus.fxml");
		root = FXMLLoader.load(location);
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void switchToGestionTrainsPage(ActionEvent e) throws IOException {
		URL location = getClass().getResource("GestionTrains.fxml");
		root2 = FXMLLoader.load(location);
		stage2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene2 = new Scene(root2);
		stage2.setScene(scene2);
		stage2.show();
	}
	public void switchToGestionUsersPage(ActionEvent e) throws IOException {
		URL location = getClass().getResource("CrudUser.fxml");
		root3 = FXMLLoader.load(location);
		stage3 = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene3 = new Scene(root3);
		stage3.setScene(scene3);
		stage3.setMaximized(true);
		stage3.show();
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
//		if (UserSession.getRole().equals("administrateur")) {
//			btnBarUser.setManaged(false);
//		} else {
//			btnBarAdmin.setManaged(false);
//		}
	}
}
