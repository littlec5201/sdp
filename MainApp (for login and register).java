package address;

import java.io.IOException;

import address.view.RegisterLayoutController;
import address.view.WelcomeLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static Stage welcomeStage, registerStage, loginStage;
    private BorderPane rootLayout;

    public MainApp() {

    }

    @Override
    public void start(Stage primaryStage) {
        this.welcomeStage = primaryStage;
        this.welcomeStage.setTitle("Financial Calculator");

        rootLayout();

        showWelcomeOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void rootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/InitialLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            welcomeStage.setScene(scene);
            welcomeStage.show();
            welcomeStage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showWelcomeOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/WelcomeOverview.fxml"));
            AnchorPane registerOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(registerOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}