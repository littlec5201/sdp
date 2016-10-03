package com.development.software.finance.view;

import java.io.File;

import com.development.software.finance.MainApp;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class RootLayoutController {

	//Reference to main application
	private MainApp mainApp;

	/**
	 * Is called by the main application to give reference to itself
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}


	// check main for getUserFilePath and setFilePath methods
	@FXML
	private void handleSave(){
		File expenseFile = mainApp.getBudgetFilePath(); //////////////// change?
		if(expenseFile !=null){
			mainApp.saveBudgetDataToFile(expenseFile);
		}else{
			handleSaveAs();
		}
	}

	@FXML
	private void handleSaveAs(){
		FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveBudgetDataToFile(file);
        }
	}



	@FXML
	private void handleExit(){
		System.exit(0);
	}

}
