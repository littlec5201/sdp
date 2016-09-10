package com.development.software.finance.view;

import com.development.software.finance.model.Expense;
import com.development.software.finance.model.Job;
import com.development.software.finance.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
	private TextField jobName;
	@FXML
	private TextField hoursPerWeek;
	@FXML
	private TextField payPerWeek;
	@FXML
	private TextField hourlyPay;
	@FXML
	private TextField payDay;

	@FXML
	private TextField expenseName;
	@FXML
	private TextField expense;
	@FXML
	private RadioButton repeat;

	@FXML
	private Label userID;

	private User user;

	public UserOverviewController(){

	}

	private void showJobDetails(Job job){
		if(job != null){
			jobName.setText(job.getJobName());
			payPerWeek.setText("$" + job.getJobIncome());
			hoursPerWeek.setText("");
			hourlyPay.setText("");
			payDay.setText("");
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
			this.expense.setText("$" + expense.getCost());
			expenseName.setText(expense.getExpenseName());
		}else{
			this.expense.setText("");
			expenseName.setText("");

		}
	}

	@FXML
	private void initialize(){
		System.out.println("Test");
		jobNameColumn.setCellValueFactory(cellData -> cellData.getValue().jobnameProperty());
		jobFloatColumn.setCellValueFactory(cellData -> cellData.getValue().jobIncomeProperty().asObject());
		//jobFloatColumn.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().jobIncomeProperty().get()));
		expenseNameColumn.setCellValueFactory(cellData -> cellData.getValue().expensesNameProperty());
		expenseFloatColumn.setCellValueFactory(cellData -> cellData.getValue().expensesCostProperty().asObject());
		//expenseFloatColumn.setCellValueFactory(cellData -> new SimpleStringProperty("" + cellData.getValue().expensesCostProperty().get()));

		showJobDetails(null);
		showExpenseDetails(null);

		jobTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showJobDetails(newValue));
		expenseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue2) -> showExpenseDetails(newValue2));
	}

	public void setUser(User user){
		this.user = user;

		//System.out.print("Test");
		jobTable.setItems(user.getJobsData());
		expenseTable.setItems(user.getExpencesData());
	}
}
