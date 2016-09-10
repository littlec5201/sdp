package com.development.software.finance.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Expense {

	private StringProperty expenseName;
	private FloatProperty cost;

	public Expense(String name, float cost){
		this.expenseName = new SimpleStringProperty(name);
		this.cost = new SimpleFloatProperty(cost);
	}

	public StringProperty expensesNameProperty(){
		return this.expenseName;
	}

	public FloatProperty expensesCostProperty(){
		return this.cost;
	}

	public String getExpenseName(){
		return expenseName.get();
	}

	public void setExpenseName(String name){
		expenseName = new SimpleStringProperty(name);
	}

	public float getCost(){
		return cost.get();
	}

	public void setCost(float cost){
		this.cost = new SimpleFloatProperty(cost);

	}

	public String toString(){
		return expenseName.get() + ": $"+ cost.get();
	}

}
