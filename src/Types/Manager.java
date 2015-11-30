package Types;
import Controls.*;

/**
 * Manager class, extends User
 */
public class Manager extends User {

	/**
	 * Manager constructor
	 * @param UN
	 * @param PW
	 */
	public Manager(String UN, String PW){

		super(UN, PW);
		this.accountType = "Manager";	//set the account type to Manager
	}
	
	/**
	 * Manager has the ability to accept the application from a user
	 * @param U
	 */
	public static void acceptApplication(User U){

		Driver D = new Driver();
	}
}