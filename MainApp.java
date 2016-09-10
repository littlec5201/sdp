package com.development.software.finance;

import java.io.IOException;

import com.development.software.finance.model.User;
import com.development.software.finance.view.UserOverviewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private User user;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Finance App");

		initRootLayout();

		showUserOverview();
	}

	public void initRootLayout(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	public void showUserOverview(){
		try {
			user = new User("Rudy", "Lang", 1200.22f);
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/UserOverview.fxml"));
			AnchorPane userOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(userOverview);

			UserOverviewController controller = loader.getController();
			controller.setUser(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage(){
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
