public class Job {
	private String jobName;
	private int jobIncome;
	private String payDay;
	
	public Job(String jobname, int income, String paymentDate){
		this.jobName = jobName;
		jobIncome = income;
		payDay = paymentDate;
	}
	
	/**
	 * Returns the name of the job.
	 * @return
	 */
	public String getJobName(){
		return jobName;
	}
	
	/**
	 * Returns the weekly income of this job.
	 * @return
	 */
	public int getJobIncome(){
		return jobIncome;
	}
	
	/**
	 * Returns the pay date of this job.
	 * @return
	 */
	public String getPayDayDate(){
		return payDay;
	}
	
	/**
	 * Returns a String representation of the job.
	 */
	public String toString(){
		String jobString = jobName + ": " + jobIncome + " : " + payDay;
		
		return jobString;
	}
}
