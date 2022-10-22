package application2;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PasswordController {
	public void resetpass(ActionEvent e) {
		try {
	URL location = getClass().getResource("Password.fxml");
	Parent root = FXMLLoader.load(location);
	Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	 Scene scene = new Scene(root);
	stage.setScene(scene);
	stage.show();
}catch(Exception ex) {
	ex.printStackTrace();
}
}
}
