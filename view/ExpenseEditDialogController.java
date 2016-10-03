package com.development.software.finance.view;

import com.development.software.finance.model.Expense;
import com.development.software.finance.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ExpenseEditDialogController {

	@FXML
	private TextField expenseField;

	@FXML
	private TextField costField;

	@FXML
	private TextField dateField;

	private Stage dialogStage;
	private Expense expense;
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
	public void setExpense(Expense expense){
		this.expense = expense;

		expenseField.setText(expense.getExpenseName());
		costField.setText(Float.toString(expense.getCost()));
		dateField.setText(DateUtil.format(expense.getDate()));
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
			expense.setExpenseName(expenseField.getText());
			expense.setCost(Float.parseFloat(costField.getText()));
			expense.setDate(DateUtil.parse(dateField.getText()));

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

		if(expenseField.getText() == null || expenseField.getText().length() == 0){
			errorMessage += "No valid expense name!\n";
		}
		if(costField == null || costField.getText().length() == 0){
			errorMessage += "No valid cost!\n";
		}else{
			try{
				Float.parseFloat(costField.getText());
			}catch(NumberFormatException e){
				errorMessage += "No valid cost (Must be a number)";
			}
		}
		if(dateField.getText() == null || dateField.getText().length() == 0){
			errorMessage += "No valid date!\n";
		}else{
			if(!DateUtil.validDate(dateField.getText())){
				errorMessage += "No valid date. Use the format dd.mm.yyyy!\n";
			}
		}

		if(errorMessage.length() == 0){
			return true;
		}else{
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
