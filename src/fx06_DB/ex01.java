package fx06_DB;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ex01 extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("eventTest.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Controller ct1 = loader.getController();
		ct1.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
