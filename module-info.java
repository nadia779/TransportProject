module JavaFXProjetS1 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires javafx.web;
	opens application to javafx.graphics, javafx.fxml;
	exports projets1;
	opens projets1 to javafx.graphics;
	exports incbuspos;
	opens incbuspos to javafx.graphics, javafx.fxml;
	exports models;
	opens models to javafx.graphics, javafx.fxml;
//	module javafx.base cannot access class .Utilisateurs
//	(in module JavaFXProjetS1)
//	because module JavaFXProjetS1 does not open model to javafx.base
}
