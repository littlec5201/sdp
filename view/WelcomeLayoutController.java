package address.view;

import java.io.IOException;

import address.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WelcomeLayoutController {
	public static Stage welcomeStage, loginStage, registerStage;

	@FXML
	public void handleLogin() {
		try {
			welcomeStage = MainApp.welcomeStage;
			MainApp.welcomeStage.hide();
	    	loginStage = new Stage();
	    	loginStage.setTitle("Login");
	    	BorderPane root = new BorderPane();
	    	Scene scene = new Scene(root, 300, 300);
	    	loginStage.setScene(scene);
	    	loginStage.show();
	    	loginStage.setResizable(false);

	    	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WelcomeLayoutController.class.getResource("LoginOverview.fxml"));
            AnchorPane loginOverview = (AnchorPane) loader.load();
            root.setCenter(loginOverview);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void handleRegister() {
		try {
			welcomeStage = MainApp.welcomeStage;
			MainApp.welcomeStage.hide();
	    	registerStage = new Stage();
	    	registerStage.setTitle("Register");
	    	BorderPane root = new BorderPane();
	    	Scene scene = new Scene(root, 300, 300);
	    	registerStage.setScene(scene);
	    	registerStage.show();
	    	registerStage.setResizable(false);

	    	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WelcomeLayoutController.class.getResource("RegisterOverview.fxml"));
            AnchorPane registerOverview = (AnchorPane) loader.load();
            root.setCenter(registerOverview);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void handleExit() {
		System.exit(0);
	}
}
