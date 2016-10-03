package com.development.software.finance.view;



import java.time.LocalDate;

import com.development.software.finance.MainApp;
import com.development.software.finance.model.Expense;
import com.development.software.finance.model.Job;
import com.development.software.finance.model.User;
import com.development.software.finance.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UserOverviewController {

	@FXML
	private TableView<Job> jobTable;
	@FXML
	private TableColumn<Job, String> jobNameColumn;
	@FXML
	private TableColumn<Job, Float> jobFloatColumn;
	@FXML
	private TableView<Expense> expenseTable;
	@FXML
	private TableColumn<Expense, String> expenseNameColumn;
	@FXML
	private TableColumn<Expense, Float> expenseFloatColumn;

	@FXML
	private Label jobName;
	@FXML
	private Label hoursPerWeek; // not sure if this will be necessary
	@FXML
	private Label payPerWeek; // not sure if this will be necessary
	@FXML
	private Label hourlyPay; // not sure if this will be necessary
	@FXML
	private Label payDay; // will use this for date initially
	@FXML
	private Label currentDate;

	@FXML
	private Label expenseName;
	@FXML
	private Label expense;
	@FXML
	private Label expenseDate;
	@FXML
	private RadioButton repeat; // this functionality will be added later

	@FXML
	private Label userID;

	@FXML
	private Label savings;

	@FXML
	private User user; // not sure if necessary

	private MainApp mainApp;

	/**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
	public UserOverviewController(){

	}

	@FXML
	private void initialize(){
		System.out.println("Test");

		// Initialize the two tables with two columns each (name and price)
		jobNameColumn.setCellValueFactory(cellData -> cellData.getValue().jobnameProperty());
		jobFloatColumn.setCellValueFactory(cellData -> cellData.getValue().jobIncomeProperty().asObject());
		//jobFloatColumn.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().jobIncomeProperty().get()));
		expenseNameColumn.setCellValueFactory(cellData -> cellData.getValue().expensesNameProperty());
		expenseFloatColumn.setCellValueFactory(cellData -> cellData.getValue().expensesCostProperty().asObject());
		//expenseFloatColumn.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().expensesCostProperty().get()));

		showJobDetails(null);
		showExpenseDetails(null);

		// change listeners
		jobTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showJobDetails(newValue));
		//expensetable below newValue changed from newValue2 (not sure why it was newValue2	)
		expenseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showExpenseDetails(newValue));
		// add change listener for savings? or savings will be changed via other methods - may not be needed
	}

	private void showJobDetails(Job job){
		if(job != null){
			jobName.setText(job.getJobName());
			payPerWeek.setText("$" + job.getJobIncome());
			hoursPerWeek.setText("");
			hourlyPay.setText("");
			payDay.setText(DateUtil.format(job.getDate()));
		}else{
			jobName.setText("");
			payPerWeek.setText("");
			hoursPerWeek.setText("");
			hourlyPay.setText("");
			payDay.setText("");

		}
	}

	private void showExpenseDetails(Expense expense){
		if(expense != null){
			this.expense.setText("$"+expense.getCost());
			expenseName.setText(expense.getExpenseName());
			expenseDate.setText(DateUtil.format(expense.getDate()));
		}else{
			this.expense.setText("");
			expenseName.setText("");
			expenseDate.setText("");

		}
	}

	// was here for testing purposes... may be used when multiple user functionality is added
	// not being currently used at all
	public void setUser(User user){
		this.user = user;

		//System.out.print("Test");
		jobTable.setItems(user.getJobsData());
		expenseTable.setItems(user.getExpencesData());
	}

	/**
     * Is called by the main application to give a reference back to itself.
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
     // currently index 0 is set to default, this will be changed once multiple users is an option
        jobTable.setItems(mainApp.getUserData().get(0).getJobsData());
        expenseTable.setItems(mainApp.getUserData().get(0).getExpencesData());
        userID.setText(mainApp.getUserData().get(0).getUserName());
        savings.setText("$"+mainApp.getUserData().get(0).getSavings());
        // add system date //////////////////////////////////////////////////////////////////////
        currentDate.setText(DateUtil.format(LocalDate.now()));
    }

    @FXML
    private void handleDeleteJob(){
    	int selectedIndex = jobTable.getSelectionModel().getSelectedIndex();
    	if(selectedIndex >=0){
    		jobTable.getItems().remove(selectedIndex);
    	}
    	else{
    		 Alert alert = new Alert(AlertType.WARNING);
    	     alert.initOwner(mainApp.getPrimaryStage());
    	     alert.setTitle("No Selection");
    	     alert.setHeaderText("No job Selected");
    	     alert.setContentText("Please select a job in the table.");

    	     alert.showAndWait();
    	}
    }

    @FXML
    private void handleDeleteExpense(){
    	int selectedIndex = expenseTable.getSelectionModel().getSelectedIndex();
    	if(selectedIndex >= 0){
    		expenseTable.getItems().remove(selectedIndex);
    	}
    	else{
    		 Alert alert = new Alert(AlertType.WARNING);
    	     alert.initOwner(mainApp.getPrimaryStage());
    	     alert.setTitle("No Selection");
    	     alert.setHeaderText("No expense Selected");
    	     alert.setContentText("Please select a expense in the table.");

    	     alert.showAndWait();
    	}
    }

    /**
	 * Called when the user clicks the new expense button. Opens a dialog to edit
	 * details for a new expense.
	 */
	@FXML
	private void handleNewExpense() {
	    Expense tempExpense = new Expense();
	    boolean okClicked = mainApp.showExpenseEditDialog(tempExpense);
	    if (okClicked) {
	    	mainApp.getUserData().get(0).getExpencesData().add(tempExpense); // Another place to change when adding multi user functionality
	    }
	}

	/**
	 * Called when the user clicks the edit expense button. Opens a dialog to edit
	 * details for the selected expense.
	 */
	@FXML
	private void handleEditExpense() {
	    Expense selectedExpense = expenseTable.getSelectionModel().getSelectedItem();
	    if (selectedExpense != null) {
	        boolean okClicked = mainApp.showExpenseEditDialog(selectedExpense);
	        if (okClicked) {
	            showExpenseDetails(selectedExpense);
	        }

	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Expense Selected");
	        alert.setContentText("Please select an expense in the table.");

	        alert.showAndWait();
	    }
	}

	/**
	 * Called when the user clicks the new job button. Opens a dialog to edit
	 * details for a new expense.
	 */
	@FXML
	private void handleNewJob() {
	    Job tempJob = new Job();
	    boolean okClicked = mainApp.showJobEditDialog(tempJob);
	    if (okClicked) {
	    	mainApp.getUserData().get(0).getJobsData().add(tempJob); // Another place to change when adding multi user functionality
	    }
	}

	/**
	 * Called when the user clicks the edit job button. Opens a dialog to edit
	 * details for the selected expense.
	 */
	@FXML
	private void handleEditJob() {
	    Job selectedJob = jobTable.getSelectionModel().getSelectedItem();
	    if (selectedJob != null) {
	        boolean okClicked = mainApp.showJobEditDialog(selectedJob);
	        if (okClicked) {
	            showJobDetails(selectedJob);
	        }

	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Job Selected");
	        alert.setContentText("Please select a job in the table.");

	        alert.showAndWait();
	    }
	}
}