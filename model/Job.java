package com.development.software.finance.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Job {

	private StringProperty jobName;
	private FloatProperty jobIncome;

	public Job(String jobName, float income){
		this.jobName = new SimpleStringProperty(jobName);
		this.jobIncome = new SimpleFloatProperty(income);

	}

	/**
	 * Returns the name of the job.
	 * @return
	 */
	public String getJobName(){
		return jobName.get();
	}

	public void setJobName(String jobName){
		this.jobName = new SimpleStringProperty(jobName);
	}

	public StringProperty jobnameProperty(){
		return this.jobName;
	}

	/**
	 * Returns the weekly income of this job.
	 * @return
	 */
	public float getJobIncome(){
		return jobIncome.get();
	}

	public void setJobIncome(float income){
		this.jobIncome = new SimpleFloatProperty(income);
	}

	public FloatProperty jobIncomeProperty(){
		return this.jobIncome;
	}


	public String toString(){
		String jobString = jobName.get() + ": $" + jobIncome.get();

		return jobString;
	}

}
