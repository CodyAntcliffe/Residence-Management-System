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
		
		
		
		
		
		
		
		
		/*
		 * Login and register should be within driver class
		 * Manager driver and student driver, extends driver
		 * create student class which combines resident and applicant.  Have access to student driver.  
		 * ManagerDriver and StudentDrive have different access rights to the database. 
		 * Room class, need to add to db.  
		 * Web-page to print out rooms with drop down menus.
		 * Methods to get array of rooms names.  ViewRoomInfo returns object of type room.  
		 * Table for applications. Student can create an application via ApplyForResidence.Manager can access these via ManagerDriver, where it returns array fo these objects.
		 * All driver call should be encapsulated by a method call in Student or Manager or User.
		 * Login should first check if credentials exist/true.  Return accountType if successful, or null otherwise.
		 * As many driver access methods as we can think of.
		 * test
		 */
		
	
	}
}
