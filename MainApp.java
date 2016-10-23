package com.development.software.finance;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.development.software.finance.model.BudgetListWrapper;
import com.development.software.finance.model.Expense;
import com.development.software.finance.model.Job;
import com.development.software.finance.model.User;
import com.development.software.finance.view.ExpenseEditDialogController;
import com.development.software.finance.view.JobEditDialogController;
import com.development.software.finance.view.LoginApp;
import com.development.software.finance.view.MonthlyGraphController;
import com.development.software.finance.view.RootLayoutController;
import com.development.software.finance.view.UserOverviewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Stage {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private User user; // this will probably be needed later

	private String username; ///////////////////////////

	// maybe
	private String expenseFilePath;
	private String jobFilePath;

	private LoginApp loginApp;

	// use a list of users
	// additional functionality to select user will utilize login feature
	/**
     * The data as an observable list of Users.
     */
    private ObservableList<User> users = FXCollections.observableArrayList();

    public Stage getPrimaryStage(){
		return primaryStage;
	}

	public ObservableList<User> getUserData() {
        return users;
    }

	// main constructor takes in username string
	// calls correct user
	public MainApp(String username){
		this.primaryStage = new Stage();
		this.primaryStage.setTitle("Finance App");
		this.username = username;

		// populate users list with current users in system

		// list of initial users for testing
		users.add(new User("Squidface","Spongebob","Squarepants",1000.50f));
		users.get(0).getExpencesData().add(new Expense("Rent",500.00f));
		users.get(0).getExpencesData().add(new Expense("Power",40.00f));
		users.get(0).getExpencesData().add(new Expense("Internet",99.99f));
		users.get(0).getJobsData().add(new Job("Work",1000.50f));
		users.get(0).getJobsData().add(new Job("Sexual Favours",330.00f));
		users.get(0).getJobsData().add(new Job("Bribes",200.00f));

		users.add(new User("GOD","Carl","Stevens",5123.47f));
		users.get(1).getExpencesData().add(new Expense("Rent",200.00f));
		users.get(1).getExpencesData().add(new Expense("Power",40.00f));
		users.get(1).getJobsData().add(new Job("Work",700.31f));

//		if(users.contains(user)); //////////////////////////

//		selectUser();

		initRootLayout();
		showUserOverview();
	}

//	public MainApp(String userName){
//
//	}

//	@Override
//	public void start(Stage primaryStage) {
//		this.primaryStage = primaryStage;
//		this.primaryStage.setTitle("Finance App");
//
//		// idea... call Layout method for login... login method calls appropriate root layout for selected user
//		// this.user = selectedUser
//		// change code to reflect this selection
////		loginApp
////		loginApp = new LoginApp();
//
//
//		// list of initial users for testing
//		users.add(new User("Spongebob","Squarepants",1000.50f));
//		users.get(0).getExpencesData().add(new Expense("Rent",500.00f));
//		users.get(0).getExpencesData().add(new Expense("Power",40.00f));
//		users.get(0).getExpencesData().add(new Expense("Internet",99.99f));
//		users.get(0).getJobsData().add(new Job("Work",1000.50f));
//		users.get(0).getJobsData().add(new Job("Sexual Favours",330.00f));
//		users.get(0).getJobsData().add(new Job("Bribes",200.00f));
//
//		users.add(new User("Carl","Stevens",5123.47f));
//		users.get(1).getExpencesData().add(new Expense("Rent",200.00f));
//		users.get(1).getExpencesData().add(new Expense("Power",40.00f));
//		users.get(1).getJobsData().add(new Job("Work",700.31f));
//
////		if(users.contains(user)); //////////////////////////
//
////		selectUser();
//
//		initRootLayout();
//		showUserOverview();
//	}

	//Initialise user method will go here///////////////////////////////////////////////////////
	public void selectUser(){
		// create fxml layout for login/register
		// select user for list or add to list
		// assign local user object to selected user
		//call initRootLayout?
	}

	public void initRootLayout(){
		try{
			// load layout from fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// show scene containing root layout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// give controller access to the main app
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();
		} catch(IOException e){
			e.printStackTrace();
		}

		File file = getBudgetFilePath();
		// change check for if file is empty not null?
//		if(file !=null){
//			loadBudgetDataFromFile(file);
//		}

		if(file.length() == 0){
			loadBudgetDataFromFile(file);
		}
	}

	public void showUserOverview(){
		try {
			user = users.get(0); // set to 0 while multiple user functionality isn't implemented

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/UserOverview.fxml"));
			AnchorPane userOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(userOverview);

			UserOverviewController controller = loader.getController();
//			controller.setUser(user); //original for testing, no longer used
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a dialog to edit details for the specified expense. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 *
	 * @param expense the expense object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showExpenseEditDialog(Expense expense){
		try{
			// Load the FXML file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/ExpenseEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Expense");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the expense into the controller.
	        ExpenseEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setExpense(expense);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();


	        return controller.isOkClicked();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Opens a dialog to edit details for the specified expense. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 *
	 * @param expense the expense object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showJobEditDialog(Job job){
		try{
			// Load the FXML file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/JobEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Job");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the expense into the controller.
	        JobEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setJob(job);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This is returns the file preference:
	 * the file that was last opened
	 *
	 */
	public File getBudgetFilePath() {
		// return null
//	    Preferences prefs = Preferences.userNodeForPackage(MainApp.class); // change this
//	    String filePath = prefs.get("filePath", null);

		// check if file exists?

		String filePath = username+".xml";
//	    if (filePath != null) {
//	        return new File(filePath);
//	    } else {
//	        return null;
//	    }
	    return new File(filePath);
	}

	/**
	 * Sets the file path for the currently loaded file
	 */
	public void setFilePath(File file){
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class); // may need to remove this
	    if (file != null) {
	        prefs.put("filePath", file.getPath());

	    } else {
	        prefs.remove("filePath");
	    }
	}


	/**
	 * Loads the budget data from the specified file. Current expense data will be replaced
	 * @param File
	 */
	public void loadBudgetDataFromFile(File file){
		try{
			JAXBContext context = JAXBContext.newInstance(BudgetListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			// Reading XML from file and unmarshalling
				BudgetListWrapper wrapper = (BudgetListWrapper)um.unmarshal(file);

			//  Another place to change once multi-users are added ///////////////////////////////
			users.get(0).getExpencesData().clear();
			users.get(0).getJobsData().clear();
			users.get(0).getExpencesData().addAll(wrapper.getExpenses());
			users.get(0).getJobsData().addAll(wrapper.getJobs());


			// Save the file to the registry
			setFilePath(file);
		}catch(Exception e){
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
//	        alert.setTitle("Error");
//	        alert.setHeaderText("Could not load data");
//	        alert.setContentText("Could not load data from file:\n" + file.getPath());
//
//	        alert.showAndWait();
		}
	}


	/**
	 * Saves the current expense data to the specified file
	 * @param file
	 */
	public void saveBudgetDataToFile(File file){
		try {
	        JAXBContext context = JAXBContext
	                .newInstance(BudgetListWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // Wrapping our person data.
	        BudgetListWrapper wrapper = new BudgetListWrapper();
	        wrapper.setUserName(users.get(0).getUserName());
	        wrapper.setExpenses(users.get(0).getExpencesData()); // another place to replace //////////////////////
	        wrapper.setJobs(users.get(0).getJobsData()); // /////////////////////////////////////////////////////

	        // Marshalling and saving XML to the file.
	        m.marshal(wrapper, file);

	        // Save the file path to the registry.
	        setFilePath(file);
	    } catch (Exception e) {
	    	e.printStackTrace();
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Could not save data");
	        alert.setContentText("Could not save data to file:\n" + file.getPath());

	        alert.showAndWait();
	    }
	}

	public void showMonthlyGraph() {
	    try {
	        // Load the fxml file and create a new stage for the popup.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/MonthlyGraph.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Monthly Graph!");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the persons into the controller.
	        MonthlyGraphController controller = loader.getController();
	        //
	        controller.setPersonData(users.get(0)); //////////////// this will need to be changed when multiple cunts are used

	        dialogStage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public static void main(String[] args) {
//		launch(args);
	}
}