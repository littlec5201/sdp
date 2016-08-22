import java.util.ArrayList;

public class User {
	private String userID;
	private String fName, lName;
	private ArrayList<Job> jobs;
	private int savings;
	
	public User(String userID){
		downloadUser(userID);
		
		
	}
	
	/**
	 * Returns the total income per week for all jobs
	 * @return
	 */
	public int getIncome(){
		int totalIncome = 0;
		for(Job thisJob: jobs){
			totalIncome += thisJob.getJobIncome();
		}
		
		return totalIncome;
	}
	
	
	/**
	 * Returns a String array of information on all jobs for this user
	 * with a numbered position in the ArrayList.
	 * @return
	 */
	public String[] getJobs(){
		String[] jobList = new String[jobs.size()];
		int count = 0;
		for(Job thisJob: jobs){
			jobList[count] = count + ": " + thisJob.toString();
		}
		
		return jobList;
	}
	
	/**
	 * Returns the savings of this user.
	 * @return
	 */
	public int getSavings(){
		return savings;
	}
	
	/**
	 * Adds money to the savings reference.
	 * @param change
	 */
	public void addToSavings(int change){
		savings += change;
	}
	
	/**
	 * Removes money from the savings reference.
	 * @param change
	 */
	public void removeFromSavings(int change){
		savings -= change;
	}
	
	/**
	 * Updates the savings reference to a new number;
	 * @param newSavings
	 */
	public void updateSavings(int newSavings){
		savings = newSavings;
	}
	
	/**
	 * Returns the full name of the user.
	 * @return
	 */
	public String getName(){
		String name = fName + " " + lName;
		
		return name;
	}
	
	/**
	 * Returns the first name of the user.
	 * @return
	 */
	public String getFirstName(){
		return fName;
	}
	
	/**
	 * Returns the last name of the user.
	 * @return
	 */
	public String getLastName(){
		return lName;
	}
	
	/**
	 * Returns the userID of this user account.
	 * @return
	 */
	public String getUserID() {
		return userID;
	}
	
	
	/**
	 * Communicates with the fileIO to save all the information on
	 * the user in local memory for reference.
	 * To be called when first creating this user object.
	 * @param userID
	 */
	private void downloadUser(String userID){
		
	}
	
	/**
	 * Communicates with the fileIO to save all the information on
	 * local storage and the SQL server.
	 * Should be called before closing the application when save on close
	 * is requested.
	 */
	private void saveUser(){
		
	}
	
	
	
}
