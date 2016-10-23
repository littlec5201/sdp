package com.development.software.finance.view;

import java.time.LocalDate;
import java.util.ArrayList;

import com.development.software.finance.model.Expense;
import com.development.software.finance.model.Job;
import com.development.software.finance.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class MonthlyGraphController {

	@FXML
	private LineChart<String, Number> lineChart;

	@FXML
	private CategoryAxis xAxis;

	private ObservableList<String> dateNames = FXCollections.observableArrayList();

	/**
	 * Initializes the controller class for the Line Chart.
	 * This is called after the FXML file has been loaded.
	 */
	@FXML
	private void initialize(){
		String[] days = new String[30];
		LocalDate todaysDate = LocalDate.now();
		for(int i = 0; i < 30; i++){
			days[29-i] = todaysDate.minusDays(i).toString();
		}

		dateNames.addAll(days);
		System.out.println(dateNames.size());

		xAxis = new CategoryAxis();
		xAxis.setCategories(dateNames);
//		lineChart = new LineChart<String, Float>(xAxis, null);

	}

	/**
	 * Sets the person to show this months staticstics
	 */
	public void setPersonData(User user){

		ObservableList<Expense> expenses = user.getExpencesData();
		ObservableList<Job> jobs = user.getJobsData();

		ArrayList<Expense> expensesToUse = new ArrayList<Expense>();
		ArrayList<Job> jobsToUse = new ArrayList<Job>();
		LocalDate todaysDate = LocalDate.now();
		LocalDate firstDate = LocalDate.now().minusDays(30);
//		float[] tempDayCounter = new float[31];
		float[] dayCounter = new float[31];

		ArrayList<Float> tempDayCounter = new ArrayList<Float>();

		for(int i = 0; i < 31; i++){
			tempDayCounter.add(0.0f);
			dayCounter[i] = 0.0f;
		}

//		dayCounter[29] = user.getSavings();
		float tempMinimum = 0;
		for(Expense expense: expenses){
			if(expense.getDate().compareTo(firstDate) <= 0){
				tempDayCounter.set(expense.getDate().getDayOfMonth(), tempDayCounter.get(expense.getDate().getDayOfMonth()) - expense.getCost());

			}
		}
		for(Job job: jobs){
			if(job.getDate().compareTo(firstDate) <=0){
				tempDayCounter.set(job.getDate().getDayOfMonth(), tempDayCounter.get(job.getDate().getDayOfMonth()) + job.getJobIncome());
			}
		}
		for(int i = 0; i < 30; i++){
			if(i == 0){
				dayCounter[29] = user.getSavings();
				if(tempMinimum > user.getSavings()){
					tempMinimum = user.getSavings();
				}
			}
			else{
				//System.out.println(i);
				dayCounter[29-i] = dayCounter[29-i+1] + tempDayCounter.get(todaysDate.minusDays(i).getDayOfMonth());
				if(tempMinimum > dayCounter[29-i]){
					tempMinimum = dayCounter[29-i];
				}
			}
		}

		XYChart.Series<String, Number> series = new XYChart.Series<>();

		for(int i = 0; i < dayCounter.length-1; i++){
			String tempString = dateNames.get(i);
			float tempFloat = dayCounter[i];
			series.getData().add(new XYChart.Data<>(tempString, tempFloat));
		}
		int axis[] = new int[10];
		for(int i = 0; i < 10; i++){
			axis[i] = (int) ((user.getSavings()/10)*(i+1));
		}


		NumberAxis axisY = new NumberAxis();
//		axisY.setMaxHeight(2000);
		axisY.autosize();
//		axisY.setMinHeight(-2000);
		
		lineChart = new LineChart<String, Number>(xAxis, axisY);
		lineChart.getData().add(series);
	}
}
