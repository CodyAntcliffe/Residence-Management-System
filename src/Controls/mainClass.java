package Controls;
import Types.*;
import Controls.*;
/*THIS IS THE MAIN CLASS */

//PLEASE USE THIS FOR TESTING IN ORDER TO KEEP OTHER CLASS FILES CLEAN!

public class mainClass {
	
	public static void main(String[] args){
		Room[] roomList;
		StudentDriver SD = new StudentDriver();
		
		ManagerDriver MD = new ManagerDriver();
		
		Driver D = new Driver();
		roomList = D.getAvailRooms();
		
		for (int i=0;i<roomList.length;i++)
			System.out.println(roomList[i]);
		
		/*
		 * All driver call should be encapsulated by a method call in Student or Manager or User.
		 * Login should first check if credentials exist/true.  Return accountType if successful, or null otherwise.
		 */
		
	
	}
}
