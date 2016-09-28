package address.view;

import java.io.IOException;

import address.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginLayoutController {

	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordField;

	@FXML
	public void handleLogin() {
		String username = usernameTextField.getText();
		String password = passwordField.getText();
		Login l = new Login(username, password);
		usernameTextField.setText("");
		passwordField.setText("");
	}

	@FXML
	public void handleHome() {
		WelcomeLayoutController.loginStage.hide();
    	WelcomeLayoutController.welcomeStage.show();
	}
}
