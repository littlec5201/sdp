package com.development.software.finance.view;

import com.development.software.finance.model.Job;
import com.development.software.finance.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class JobEditDialogController {

	@FXML
	private TextField jobNameField;

	@FXML
	private TextField hoursField; // i don't like this feature, we will discuss

	@FXML
	private TextField weeklyPayField; // this has been labelled income in the Gui for current 1 time purposes

	@FXML
	private TextField hourlyPayField; // i don't like this feature, we will discuss

	@FXML
	private TextField dateField;

	// will need another component for Total earned if implemented -> recurrence issues next sprint

	private Stage dialogStage;
	private Job job;
	private boolean okClicked;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the FXML file is called
	 */
	@FXML
	private void initialize(){
	}

	/**
	 * Sets the stage for this dialog
	 */
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the expense to be added to the dialog
	 */
	public void setJob(Job job){
		this.job = job;

		jobNameField.setText(job.getJobName());
		hoursField.setText(""); // currently useless
		weeklyPayField.setText(Float.toString(job.getJobIncome())); /// aaaaaaaaaaarrrrghhhhh!!!!!
		hourlyPayField.setText(""); // currently useless
		dateField.setText(DateUtil.format(job.getDate()));
		dateField.setPromptText("dd.mm.yyyy");
	}

	public boolean isOkClicked(){
		return okClicked;
	}

	/**
	 * called when user clicks ok
	 */
	@FXML
	private void handleOk(){
		if(isInputValid()){
			job.setJobName(jobNameField.getText());
			job.setJobIncome(Float.parseFloat(weeklyPayField.getText())); // using weekly pay for income
			job.setDate(DateUtil.parse(dateField.getText()));

			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * called when the user clicks cancel
	 */
	@FXML
    private void handleCancel() {
        dialogStage.close();
    }

	/**
	 * checks for valid user input
	 */
	@FXML
	private boolean isInputValid(){
		String errorMessage = "";

		if(jobNameField.getText() == null || jobNameField.getText().length() == 0){
			errorMessage += "No valid job name!\n";
		}
//		if(hourlyPayField etc...
//		if(hoursField etc...
		if(weeklyPayField.getText() == null || weeklyPayField.getText().length() == 0){
			errorMessage += "No valid income!\n";
		}else{
			try{
				Float.parseFloat(weeklyPayField.getText());
			}catch(NumberFormatException e){
				errorMessage += "No valid income (Must be a number)!\n";
			}
		}
		if(dateField.getText() == null || dateField.getText().length() == 0){
			errorMessage += "No valid date!\n";
		}else{
			if(!DateUtil.validDate(dateField.getText())){
				errorMessage += "No valid date. Use the format dd.mm.yyyy!\n";
			}
		}

		if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
	}
}
