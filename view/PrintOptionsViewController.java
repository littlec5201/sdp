package com.development.software.view;

import javafx.scene.control.Button;

import com.development.software.Main;
import com.development.software.model.Expense;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class PrintOptionsViewController {

	private Main main;

    @FXML
    private ListView<Expense> expensesListView;
    @FXML
    private ObservableList<Expense> expensesObservableList;

    @FXML
    private Button cancelButton;
    @FXML
    private Button printButton;

    @FXML
    private RadioButton textRadioButton;
    @FXML
    private RadioButton graphRadioButton;
    private boolean printText;
    
    @FXML
    private LineChart<String, Number> expensesLineChart;

    @FXML
    private ToggleGroup group;

    /*Default constructor*/
    public PrintOptionsViewController() {

    }

    /*Called automatically when the scene is loaded*/
    @FXML
    private void initialize() {
    	
    	group = new ToggleGroup();
    	
    	/*Ordinarily would be similar to the following code, using the list of the user's expenses*/
    	//expensesObservableList = user.getExpenses();

    	/*Used for testing*/
        expensesObservableList = FXCollections.observableArrayList();
        expensesObservableList.add(new Expense("Shrek is love", 666, 1));
        expensesObservableList.add(new Expense("Shrek is life", 180, 2));
        expensesObservableList.add(new Expense("Shrek is drek", 120, 3));
        expensesObservableList.add(new Expense("Shrek is cool", 345, 4));
        expensesObservableList.add(new Expense("Shrek is boys", 20, 5));
        expensesObservableList.add(new Expense("Shrek is naked", 753, 6));
        expensesObservableList.add(new Expense("Shrek is spicy", 64, 7));
        expensesObservableList.add(new Expense("Shrek is joocy", 235, 8));
        
        expensesListView.setItems(expensesObservableList);

    	
        /*Adding all expenses from the user's list of expenses to the chart*/
    	XYChart.Series series = new XYChart.Series();

        for(int i = 0; i < expensesObservableList.size(); i++){
        	series.getData().add(new XYChart.Data(""+expensesObservableList.get(i).getDateObject(), expensesObservableList.get(i).getCost()));
        }

        expensesLineChart.getData().add(series);
    }

    /*Chooses the text to print*/
    public void textRadioButtonClicked(){
    	printText = true;
    }

    /*Chooses the chart to print*/
    public void graphRadioButtonClicked(){
    	printText = false;
    }

    /*Closes the window when cancel button is clicked*/
    @FXML
    public void cancelButtonAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /*When print button is clicked, prints whichever radio button has been clicked.*/
    public void printButtonAction(){
    	PrinterJob job = PrinterJob.createPrinterJob();
    	if (job != null) {
    		if(printText){
	    		if (job.showPrintDialog(printButton.getScene().getWindow()) && job.printPage(expensesListView))
	            {
	                job.endJob();
	            }
    		}
    		else{
    			/*Will need to pass the actual graph from the graph page to this, and not the expensesLineChart as it prints too small.*/
    			if (job.showPrintDialog(printButton.getScene().getWindow()) && job.printPage(expensesLineChart))
	            {
	                job.endJob();
	            }
    		}
    	 }
    }

    public void setMainApp(Main main) {
        this.main = main;
    }

}
