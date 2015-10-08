package Types;
import Controls.LogIn;
//Super class for Manager, Applicant and Resident

public class User {
	
	int userID; //Primary key for user related database
	public String userName; // Can't change once registered, only accessible by log-in methods
	public String passWord; // Can't change once registered, only accessible by log-in methods
	public String accountType; //account type of the user
	
	public User(String UN, String PW){
		
		this.userName = UN;
		this.passWord = LogIn.encryptPassword(PW);
		this.accountType = null;
	}
	
}
