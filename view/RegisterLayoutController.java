package address.view;

import address.MainApp;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RegisterLayoutController {

	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private PasswordField confirmPasswordField;
	@FXML
	private Button homeButton;
	@FXML
	private Button registerButton;

	@FXML
	public void handleRegister() {
		String username = usernameTextField.getText();
		String password = passwordField.getText();
		String confirmPassword = confirmPasswordField.getText();
		RegisterLogic r = new RegisterLogic(username, password, confirmPassword);
		usernameTextField.setText("");
		passwordField.setText("");
		confirmPasswordField.setText("");
	}

    /**
     * Initializes the root layout.
     */
    @FXML
    public void handleHome() {
    	WelcomeLayoutController.registerStage.hide();
    	WelcomeLayoutController.welcomeStage.show();
    }
}
