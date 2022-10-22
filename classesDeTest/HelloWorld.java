package classesDeTest;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloWorld extends Application {
	private Parent createContent() {
        return new StackPane(new Text("Hello World"));
    }
	
	@Override
    public void start(Stage stage) throws Exception {
		StackPane sPane = new StackPane();
		Text txt = new Text("Hello Everyone");
		sPane.getChildren().add(txt);
        stage.setScene(new Scene(sPane, 300, 300));
        stage.show();
    }
	
	public static void main(String[] args) {
		launch();
	}

}
