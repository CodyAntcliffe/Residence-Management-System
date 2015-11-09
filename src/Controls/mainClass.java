package Controls;
import Types.*;
import Controls.*;
/*THIS IS THE MAIN CLASS */

//PLEASE USE THIS FOR TESTING IN ORDER TO KEEP OTHER CLASS FILES CLEAN!

public class mainClass {
	
	public static void main(String[] args){
	
		StudentDriver SD = new StudentDriver();
		
		ManagerDriver MD = new ManagerDriver();
		
		Driver D = new Driver();
		D.checkLogin("manager", "password");
		
		/*
		 * All driver call should be encapsulated by a method call in Student or Manager or User.
		 * Login should first check if credentials exist/true.  Return accountType if successful, or null otherwise.
		 */
		
	
	}
}
