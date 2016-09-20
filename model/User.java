package com.development.software.finance.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {

	private StringProperty firstName;
	private StringProperty lastName;
	private FloatProperty savings;

	private ObservableList<Job> jobsData = FXCollections.observableArrayList();
	private ObservableList<Expense> expencesData = FXCollections.observableArrayList();


	public User(){
		this(null, null, 0.00f);
	}

	public User(String firstName, String lastName, float savings){
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.savings = new SimpleFloatProperty(savings);

		jobsData.add(new Job("Work", 150.27f));
		jobsData.add(new Job("Student Support", 149.50f));

		expencesData.add(new Expense("Rent", 150.00f));
		expencesData.add(new Expense("Food", 33.98f));
		expencesData.add(new Expense("Water", 36.48f));
		expencesData.add(new Expense("Internet", 19.99f));
		expencesData.add(new Expense("Phone", 39.99f));
	}

	public ObservableList<Job> getJobsData(){
		return this.jobsData;
	}

	public ObservableList<Expense> getExpencesData(){
		return this.expencesData;
	}

	/**
	 * Sets the First Name
	 */
	public void setFName(String fName){
		this.firstName = new SimpleStringProperty(fName);
	}

	/**
	 * Sets the Last Name
	 */
	public void setLName(String lName){
		this.lastName = new SimpleStringProperty(lName);
	}

	/**
	 * Sets the savings
	 * will probably remove later due to security
	 */
	public void updateSavings(Float savings){
		this.savings = new SimpleFloatProperty(savings);
	}

	/**
	 * Adds to Savings
	 */
	public void addToSavings(float add){
		this.savings = new SimpleFloatProperty(this.getSavings()+add);
	}

	/**
	 * Removes from Savings
	 */
	public void removeFromSavings(float remove){
		this.savings = new SimpleFloatProperty(this.getSavings()-remove);
	}

	/**
	 * Returns the full name of the user.
	 * @return
	 */
	public String getName(){
		String name = firstName.get() + " " + lastName.get();

		return name;
	}

	/**
	 * Returns the first name of the user.
	 * @return
	 */
	public String getFirstName(){
		return firstName.get();
	}

	/**
	 * Returns the last name of the user.
	 * @return
	 */
	public String getLastName(){
		return lastName.get();
	}

	public float getSavings(){
		return savings.get();
	}

	public StringProperty firstNameProperty() {
        return firstName;
    }

	public StringProperty lastNameProperty(){
		return lastName;
	}

	public FloatProperty savingsProperty(){
		return savings;
	}

}
