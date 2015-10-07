
//Super class for Manager, Applicant and Resident

public class User {
	
	int userID; //Primary key for user related database
	public String userName; // Can't change once registered, only accessible by log-in methods
	public String passWord; // Can't change once registered, only accessible by log-in methods
	
	String accountType; //account type of the user
	
	public void register(String UN, String plainPW){
		this.userName = UN;
		this.passWord = LogIn.encryptPassword(plainPW);
		this.accountType = null;
	}
	

	
	public static void main(String[] args){
	
	}
}
