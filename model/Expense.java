package com.development.software.finance.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.development.software.finance.util.LocalDateAdapter;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Expense {

	private StringProperty expenseName;
	private FloatProperty cost;
	private final ObjectProperty<LocalDate> date;

	public Expense() {
		this(null,0);
	}

	public Expense(String name, float cost){
		this.expenseName = new SimpleStringProperty(name);
		this.cost = new SimpleFloatProperty(cost);
		this.date = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 9, 9));
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

	public ObjectProperty<LocalDate> getDateObject() {
		return date;
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDate() {
        return date.get();
    }

	public void setDate(LocalDate date) {
        this.date.set(date);
    }

}