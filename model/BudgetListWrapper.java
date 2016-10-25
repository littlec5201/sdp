package com.development.software.finance.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of expenses
 * This is used for saving a list of expensese to XML
 *
 *
 */
@XmlRootElement(name = "budget")
public class BudgetListWrapper {

	private String userName;
	private List<Expense> expenses;
	private List<Job> jobs;

	@XmlElement(name = "username")
	public String getUserName(){
		return userName;
	}

	@XmlElement(name = "expense")
	public List<Expense> getExpenses(){
		return expenses;
	}

	@XmlElement(name = "jobs")
	public List<Job> getJobs(){
		return jobs;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public void setExpenses(List<Expense> expenses){
		this.expenses = expenses;
	}

	public void setJobs(List<Job> jobs){
		this.jobs = jobs;
	}

}
