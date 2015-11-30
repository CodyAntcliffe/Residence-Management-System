package Types;
import ManagedBeans.LogIn;

/**
 * Holds the info of a User
 */
public class User {
	
	public String userName; 	// Can't change once registered, only accessible by log-in methods
	public String passWord; 	// Can't change once registered, only accessible by log-in methods
	public String accountType; 	//account type of the user
	
	/**
	 * User constructor
	 * @param UN
	 * @param PW
	 */
	public User(String UN, String PW){	
		this.userName = UN;
		this.passWord = LogIn.encryptPassword(PW);
	}
}
